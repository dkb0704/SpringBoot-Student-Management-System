package com.entity;

public class Result<T> {
    private String code;//状态码
    private String msg;//状态说明
    private T data;//携带的数据
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static Result success(String msg) {
        Result result = new Result<>();
        result.setCode("200");
        result.setMsg(msg);
        return result;
    }
    public static<T> Result<T> success(String msg,T data) {
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg(msg);
        return result;
    }

    //表示失败的result 但不携带数据
    public static<T> Result<T> error(String code,String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    //表示失败的result 但携带数据
    public static<T> Result<T> error(String code,String msg,T data) {
        Result<T> result = new Result<>(data);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
