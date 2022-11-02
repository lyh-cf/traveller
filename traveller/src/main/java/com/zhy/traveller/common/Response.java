package com.zhy.traveller.common;

import com.baomidou.mybatisplus.extension.api.R;

import java.util.HashMap;
import java.util.Map;

/*
 *文件名: Response
 *创建者: 罗义恒
 *创建时间:2022/10/30 22:15
 */
public class Response<T> {
    private Boolean success;

    private Integer code;

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据
    //把构造方法私有
    private Response() {}

    public static <T> Response<T> success(CodeMsgEnum codeMsg,T object) {
        Response<T> response = new Response<T>();
        response.data = object;
        response.code = codeMsg.getCode();
        response.msg=codeMsg.getMsg();
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

    public Response<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
