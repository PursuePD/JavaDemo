package com.cjl.common.util.encrypt.exception;

/**
 * @author: 小崔
 * @create: 2020-05-15 18:23
 * @Description:
 */
public class MyEncryptValueException extends RuntimeException  {

    public MyEncryptValueException(){
	super("属性路径不合法[A-Za-z][A-Za-z.]+$");
    }
}
