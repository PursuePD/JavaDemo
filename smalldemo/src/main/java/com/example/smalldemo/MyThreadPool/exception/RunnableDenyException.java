package com.example.smalldemo.MyThreadPool.exception;

/**
 * @author: 小崔
 * @create: 2019-12-09 15:31
 * @Description:自定义异常
 */
public class RunnableDenyException extends RuntimeException{
    public RunnableDenyException(String msg){
        super(msg);
    }
}
