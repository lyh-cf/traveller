package com.zhy.traveller.controller;

import com.zhy.traveller.common.Response;
import com.zhy.traveller.converter.UserMapper;
import com.zhy.traveller.dto.UserDTO;
import com.zhy.traveller.pojo.User;
import com.zhy.traveller.service.UserService;
import com.zhy.traveller.utils.JwtUtil;
import com.zhy.traveller.utils.MD5;
import com.zhy.traveller.utils.SendEmail;
import com.zhy.traveller.vo.UserInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static com.zhy.traveller.utils.RedisConstans.*;

/*
 *文件名: UserController
 *创建者: 罗义恒
 *创建时间:2022/10/30 22:12
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    //接收JSON格式的数据，参数名需要对应
    public Response loginUser(@RequestBody UserDTO userMsg) {
        userMsg.setPassWord(MD5.Encrypt(userMsg.getPassWord()));
        User user= UserMapper.INSTANCT.conver(userMsg);
        User result = userService.login(user);
        if (result == null) return Response.error();
        else {
            String token = JwtUtil.createToken(result.getPkId());
            redisTemplate.opsForValue().set(USER_TOKEN_KEY+token, result.getPkId(), USER_TOKEN_TTL, TimeUnit.MINUTES);
            UserInfo userInfo=UserMapper.INSTANCT.conver(result);
            userInfo.setToken(token);
            return Response.success(userInfo);
        }
    }

    @SneakyThrows
    @PostMapping("/sendCode")
    public Response sendCode(@RequestBody UserDTO userMsg) {
        int code = SendEmail.send(userMsg.getUserEmail());
        if (code == -1) return Response.error();
        //将验证码缓存到redis，有效时间5分钟
        redisTemplate.opsForValue().set(VERIFY_CODE_KEY+userMsg.getUserEmail(), code, VERIFY_CODE_TTL, TimeUnit.MINUTES);
        return Response.success();
    }

    @PostMapping("/enroll")
    public Response enroll(@RequestBody UserDTO userMsg) {
        //从Redis中获取缓存的验证码
        Object code = redisTemplate.opsForValue().get(userMsg.getUserEmail());
        if (code != null && code.equals(userMsg.getVerifyCode())) {
            if (!userService.checkUserEmailIsExist(userMsg.getUserEmail())) {
                userMsg.setPassWord(MD5.Encrypt(userMsg.getPassWord()));
                User user=UserMapper.INSTANCT.conver(userMsg);
                userService.save(user);
                redisTemplate.delete(userMsg.getUserEmail());
                return Response.success();
            } else return Response.error();
        } else return Response.error();
    }

    @RequestMapping("/signout")
    public Response signout(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        redisTemplate.delete(USER_TOKEN_KEY+token);
        return Response.success();
    }
}
