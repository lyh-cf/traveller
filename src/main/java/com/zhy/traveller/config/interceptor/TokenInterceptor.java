package com.zhy.traveller.config.interceptor;

import com.zhy.traveller.utils.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.TimeUnit;

import static com.zhy.traveller.utils.RedisConstans.USER_TOKEN_KEY;
import static com.zhy.traveller.utils.RedisConstans.USER_TOKEN_TTL;

/*
 *文件名: TokenInterceptor
 *创建者: 罗义恒
 *创建时间:2022/11/2 13:36
 */
public class TokenInterceptor implements HandlerInterceptor {
    /*
    这里TokenInterceptor未加类似@component等注解，即该类未交给Spring容器，那么也不能使用注入注解
     */
    private RedisTemplate redisTemplate;

    public TokenInterceptor(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("authorization");
        if(JwtUtil.checkToken(token)) {
            if(redisTemplate.opsForValue().get(USER_TOKEN_KEY+token)!=null){
                //刷新token有效期
                redisTemplate.expire(USER_TOKEN_KEY+token,USER_TOKEN_TTL, TimeUnit.MINUTES);
                System.out.println("请求token验证通过，已放行");
                return true;
            }
            System.out.println("token对应用户不存在，已拦截请求");
            return false;
        }
        System.out.println("token解析错误，已拦截请求");
        return false;
    }
}
