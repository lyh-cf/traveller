package com.zhy.traveller.exceptionhandler;

import com.zhy.traveller.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *文件名: MyExceptionHandle
 *创建者: 罗义恒
 *创建时间:2022/11/2 13:47
 */
@ControllerAdvice
public class MyExceptionHandle {
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Response handle(Exception e) {
        MyException myException = (MyException)e;
        return Response.error(myException.getCodeMsg());
    }
}