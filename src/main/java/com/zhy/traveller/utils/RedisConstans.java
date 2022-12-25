package com.zhy.traveller.utils;

/*
 *文件名: RedisConstans
 *创建者: 罗义恒
 *描述:
 *创建时间:2022/12/4 9:29
 */
public class RedisConstans {
    public static final String VERIFY_CODE_KEY="traveller:verifyCode:";
    public static final Long VERIFY_CODE_TTL=5L;

    public static final String USER_TOKEN_KEY="traveller:token:";
    public static final Long USER_TOKEN_TTL=30L;
}
