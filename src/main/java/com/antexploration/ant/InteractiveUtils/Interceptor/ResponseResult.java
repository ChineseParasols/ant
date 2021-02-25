package com.antexploration.ant.InteractiveUtils.Interceptor;

import java.io.Serializable;

/**
 * @author licoy.cn
 * @version 2017/11/17
 */

public class ResponseResult<T> implements Serializable {


    private Integer status;

    private T data;

    private String msg;

    private final long timestamps = System.currentTimeMillis();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public synchronized static <T> ResponseResult<T> e(ResponseCode statusEnum) {
        return e(statusEnum, null);
    }

    public synchronized static <T> ResponseResult<T> e(ResponseCode statusEnum, T data) {
        ResponseResult<T> res = new ResponseResult<T>();
        res.setStatus(statusEnum.code);
        res.setMsg(statusEnum.msg);
        res.setData(data);
        return res;
    }


    private static final long serialVersionUID = 8992436576262574064L;
}
