package com.zhy.traveller.common;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/*
 *文件名: Response
 *创建者: 罗义恒
 *创建时间:2022/10/30 22:15
 */
@Data
public class Response<T> {
    private Boolean success;

    private Integer code;

    private String msg; //错误信息

    private T data; //数据

   // private Map map = new HashMap(); //动态数据
    //把构造方法私有
    private Response() {}
    public static <T> Response<T> success() {
        Response<T> response = new Response<T>();
        response.code = CodeMsgEnum.SUCCESS.getCode();
        response.msg=CodeMsgEnum.SUCCESS.getMsg();
        response.setSuccess(true);
        return response;
    }
    public static <T> Response<T> success(T object) {
        Response<T> response = new Response<T>();
        response.data = object;
        response.code = CodeMsgEnum.SUCCESS.getCode();
        response.msg=CodeMsgEnum.SUCCESS.getMsg();
        response.setSuccess(true);
        return response;
    }
    public static <T> Response<T> error(CodeMsgEnum codeMsg) {
        Response<T> response = new Response<T>();
        response.code = codeMsg.getCode();
        response.msg=codeMsg.getMsg();
        response.setSuccess(false);
        return response;
    }
    public static <T> Response<T> error() {
        Response<T> response = new Response<T>();
        response.code = CodeMsgEnum.FAIL.getCode();
        response.msg=CodeMsgEnum.FAIL.getMsg();
        response.setSuccess(false);
        return response;
    }
    public static <T> Response<T> error(String msg) {
        Response<T> response = new Response<T>();
        response.msg=msg;
        response.setSuccess(false);
        return response;
    }
    /*
    public Response<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
     */

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
