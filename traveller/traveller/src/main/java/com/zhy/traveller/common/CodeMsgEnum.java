package com.zhy.traveller.common;

/*
 *文件名: CodeMsgEnum
 *创建者: 罗义恒
 *创建时间:2022/10/31 20:08
 */
public enum CodeMsgEnum {
    ;
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
