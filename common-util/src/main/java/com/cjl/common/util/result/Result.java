package com.cjl.common.util.result;

/**
 * @author: 小崔
 * @create: 2020-05-15 11:40
 * @Description:
 */
public class Result<T> {

    private Integer code;

    private String msg;

    private T date;

    private boolean success;

    public Result() {
    }

    public Result(Integer code, String msg, T date, boolean success) {
	this.code = code;
	this.msg = msg;
	this.date = date;
	this.success = success;
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

    public T getDate() {
	return date;
    }

    public void setDate(T date) {
	this.date = date;
    }

    public boolean isSuccess() {
	return success;
    }

    public void setSuccess(boolean success) {
	this.success = success;
    }
}
