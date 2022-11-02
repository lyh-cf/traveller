package com.zhy.traveller.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
interceptor是拦截器，filter是过滤器，两者是不一样的，filter先执行
 */
@Configuration
public class MyCorsFilter {
    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        /*
        当allowCredentials为true时，allowingOrigins不能包含特殊值“ *”，
        因为无法在“ Access-Control-Allow-Origin”响应标头上设置。
        要允许凭据具有一组来源，请明确列出它们或考虑改用“ allowedOriginPatterns”
         */
        corsConfiguration.addAllowedOriginPattern("*");//允许所有域名访问
        corsConfiguration.addAllowedHeader("*");  //允许所有请求头
        corsConfiguration.addAllowedMethod("*");  //允许所有的请求类型
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true); //允许请求携带验证信息（cookie）
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        //存储request与跨域配置信息的容器，基于url的映射
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }

}
