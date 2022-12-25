package com.zhy.traveller.config;
import com.zhy.traveller.config.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //需要在这里注册拦截器,指定一下拦截哪些部分的
        registry.addInterceptor(new TokenInterceptor(redisTemplate)).addPathPatterns("/**") //拦截所有请求，包括静态资源文件
                //排除拦截路径
                .excludePathPatterns("/", "/user/login", "/user/sendCode", "/user/enroll","/static/**"); //放行登录页，登陆操作，静态资源
    }
}
