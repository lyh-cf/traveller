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
    public static String createToken(Long userId){
        JwtBuilder jwtBuilder= Jwts.builder();
        String jwtToken=jwtBuilder
                //头信息header
                .setHeaderParam("typ","JWT")//Token类型
                .setHeaderParam("alg","HS256")//加密算法
                //载荷payload,信息
                .claim("userId",""+userId)
                //.claim("role","admin")
                //主题
                .setSubject("jwt")
                //有效时间
                //.setExpiration(new Date(System.currentTimeMillis()+expire))
                //ID
                .setId(UUID.randomUUID().toString())
                //signature签名
                .signWith(SignatureAlgorithm.HS256,secret)
                //用.拼接
                .compact();
        return jwtToken;
    }
    public static String getUserId(String token){
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(secret).parseClaimsJws(token);
        Claims claims=claimsJws.getBody();
        return (String) claims.get("userId");
    }
    public static boolean checkToken(String token){
        if(token==null){
            return false;
        }
        JwtParser jwtParser=Jwts.parser();
        try {
            Jws<Claims> claimsJws = jwtParser.setSigningKey(secret).parseClaimsJws(token);
        } catch (Exception e) {
            //不管解析的内容，只要解析异常，就返回false
            return false;
        }
        return true;

    }

    public static void main(String[] args) {
        String token1=createToken(1L);

        System.out.println(checkToken(token1));
    }
}
