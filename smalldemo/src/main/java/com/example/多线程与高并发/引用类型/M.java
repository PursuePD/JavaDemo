package com.example.多线程与高并发.引用类型;

/**
 * @author: 小崔
 * @create: 2020-05-29 15:43
 * @Description:
 */
public class M {

    @Override
    protected void finalize() throws Throwable {
	super.finalize();
	System.out.println("finalize");
    }
}
