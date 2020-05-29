package com.example.多线程与高并发.引用类型.SoftReference;

import java.lang.ref.SoftReference;

/**
 * @author: 小崔
 * @create: 2020-05-29 15:09
 * @Description:
 */
public class Test01_SoftReference {

    //启动时配置-Xmx20M
    public static void main(String[] args) {
	SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
	System.out.println(m.get());
	System.gc();

	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	System.out.println(m.get());

	byte[] b = new byte[1024*1024*11];
	System.out.println(m.get());
    }

}
