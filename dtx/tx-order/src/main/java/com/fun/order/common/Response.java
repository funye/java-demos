package com.fun.order.common;

import lombok.Data;

@Data
public class Response<T> implements java.io.Serializable {

    public static final String SUCCESS="0000";
    public static final String SUCCESS_MSG="SUCCESS";

    private String code;
    private String msg;
    private T data;
    private Long ts;

    public static <T> Response<T> ok(T data) {
        Response response = new Response();
        response.setCode(SUCCESS);
        response.setMsg(SUCCESS_MSG);
        response.setData(data);
        return response;
    }
}
