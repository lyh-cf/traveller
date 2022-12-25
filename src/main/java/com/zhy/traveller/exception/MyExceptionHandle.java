package com.zhy.traveller.exception;

import com.zhy.traveller.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *文件名: MyExceptionHandle
 *创建者: 罗义恒
 *创建时间:2022/11/2 13:47
 */
@Slf4j
@ControllerAdvice
public class MyExceptionHandle {
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Response handleMyException(MyException e) {
        log.error("业务异常:"+e);
        e.printStackTrace();
        return Response.error(e.getCodeMsg());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response handleException(Exception e) {
        log.error("系统异常:"+e);
        e.printStackTrace();
        return Response.error(e.getMessage());
    }
}