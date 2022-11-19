package com.zhy.traveller.controller;

import com.zhy.traveller.common.CodeMsgEnum;
import com.zhy.traveller.common.Response;
import com.zhy.traveller.controller.dto.UserMsg;
import com.zhy.traveller.pojo.User;
import com.zhy.traveller.service.IUserService;
import com.zhy.traveller.utils.JwtUtil;
import com.zhy.traveller.utils.MD5;
import com.zhy.traveller.utils.SendEmail;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

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
    private IUserService IUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    //接收JSON格式的数据，参数名需要对应
    public Response<User>login(HttpServletResponse response,@RequestBody UserMsg userMsg){
           userMsg.setPassWord(MD5.Encrypt(userMsg.getPassWord()));
           User result = IUserService.login(userMsg);
           if(result==null)return Response.error(CodeMsgEnum.LOGIN_FAIL);
           else{
               String token= JwtUtil.createToken(userMsg.getPkId());
               Cookie cookie = new Cookie("token", token);
               cookie.setMaxAge(60 * 60);
               response.addCookie(cookie);
               return Response.success(CodeMsgEnum.LOFIN_SUCCESS,result);
           }

    }
    @SneakyThrows
    @PostMapping("/sendCode")
    public Response sendCode(@RequestBody UserMsg userMsg){
        int code = SendEmail.send(userMsg.getUserEmail());
        if(code==-1)return Response.error(CodeMsgEnum.SENDEMAIL_FAIL);
        //将验证码缓存到redis，有效时间5分钟
        redisTemplate.opsForValue().set(userMsg.getUserEmail(),code,5, TimeUnit.MINUTES);
        return Response.success(CodeMsgEnum.SENDEMAIL_SUCCESS,null);
    }

    @PostMapping("/enroll")
    public Response<User>enroll(@RequestBody UserMsg userMsg){
            //从Redis中获取缓存的验证码
            Object code = redisTemplate.opsForValue().get(userMsg.getUserEmail());
            if(code!=null&&code.equals(userMsg.getCode())){
                if(!IUserService.checkUserEmailIsExist(userMsg.getUserEmail())){
                    userMsg.setPassWord(MD5.Encrypt(userMsg.getPassWord()));
                    IUserService.save(userMsg);
                    redisTemplate.delete(userMsg.getUserEmail());
                    System.out.println(userMsg.getPkId());
                    return Response.success(CodeMsgEnum.ENROLL_SUCCESS,userMsg);
                }
                else return Response.error(CodeMsgEnum.ENROLL_FAIL);
            }
            else return Response.error(CodeMsgEnum.ENROLL_FAIL);
    }

}
