package com.zhy.traveller.common;

import com.zhy.traveller.utils.SendEmail;

/*
 *文件名: CodeMsgEnum
 *创建者: 罗义恒
 *创建时间:2022/10/31 20:08
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 */
public enum CodeMsgEnum {
    /* 成功 */
    SUCCESS(200, "成功"),
    /* 默认失败 */
    FAIL(400, "失败"),
    /*用户错误*/
    USER_ACCOUNT_TOKEN_ERROR(2001, "Token错误");
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
