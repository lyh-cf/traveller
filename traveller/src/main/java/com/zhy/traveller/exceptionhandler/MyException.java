package com.zhy.traveller.exceptionhandler;

import com.zhy.traveller.common.CodeMsgEnum;

/*
 *文件名: MyException
 *创建者: 罗义恒
 *创建时间:2022/11/2 13:44
 */
public class MyException extends RuntimeException {
    private CodeMsgEnum codeMsg;
    public MyException(CodeMsgEnum codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg=codeMsg;
    }

    public CodeMsgEnum getCodeMsg() {
        return codeMsg;
    }
}
