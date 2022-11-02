package com.zhy.traveller.config;
import com.zhy.traveller.controller.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //需要在这里注册拦截器,指定一下拦截哪些部分的
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/user/**");
    }
}
