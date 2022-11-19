package com.zhy.traveller.common;

import com.zhy.traveller.utils.SendEmail;

/*
 *文件名: CodeMsgEnum
 *创建者: 罗义恒
 *创建时间:2022/10/31 20:08
 */
public enum CodeMsgEnum {
    LOGIN_FAIL(100,"登录失败"),
    LOFIN_SUCCESS(101,"登陆成功"),
    SENDEMAIL_FAIL(102,"验证码发送失败"),
    SENDEMAIL_SUCCESS(103,"验证码发送成功"),
    ENROLL_FAIL(104,"注册失败"),
    ENROLL_SUCCESS(105,"注册成功"),
    CHANGE_HEADIMAGE_SUCCESS(106,"更换头像成功"),
    CHANGE_HEADIMAGE_FAIL(107,"更换头像失败");
    private final int code;
    private final String msg;

    CodeMsgEnum(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
