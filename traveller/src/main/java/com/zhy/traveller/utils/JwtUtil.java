package com.zhy.traveller.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    //过期时间
    private static long expire = 60*60*1000;
    //秘钥
    private static String secret = "yiheng";
    //加密
    public static String createToken(){
        JwtBuilder jwtBuilder= Jwts.builder();
        String jwtToken=jwtBuilder
                //头信息header
                .setHeaderParam("typ","JWT")//Token类型
                .setHeaderParam("alg","HS256")//加密算法
                //载荷payload,信息
                //.claim("userName","admin")
                //.claim("role","admin")
                //主题
                .setSubject("jwt")
                //有效时间
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                //ID
                .setId(UUID.randomUUID().toString())
                //signature签名
                .signWith(SignatureAlgorithm.HS256,secret)
                //用.拼接
                .compact();
        return jwtToken;
    }
    public static boolean checkToken(String token){
        if(token==null){
            return false;
        }
        JwtParser jwtParser=Jwts.parser();
        try {
            Jws<Claims> claimsJws = jwtParser.setSigningKey(secret).parseClaimsJws(token);
            //         Claims claims=claimsJws.getBody();
//        System.out.println(claims.get("userName"));
//        System.out.println(claims.get("role"));
//        System.out.println(claims.getId());
//        System.out.println(claims.getSubject());//拿主题
//        System.out.println(claims.getExpiration());//拿存活日期
        } catch (Exception e) {
            //不管解析的内容，只要解析异常，就返回false
            return false;
//            e.printStackTrace();
        }
        return true;

    }

}
