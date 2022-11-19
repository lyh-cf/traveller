package com.zhy.traveller.utils;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GetCookieValue {
    public static String getValue(ServletRequest servletRequest,String name){
        //强制转换
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        if(cookies==null||cookies.length==0)return null;
        for (Cookie cookie : cookies) {
            if(name.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }
}
