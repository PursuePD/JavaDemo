package com.example.smalldemo.HashMap;

/**
 * @author: 小崔
 * @create: 2019-11-26 11:27
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
	MyHashMap<Integer,String> map = new MyHashMap(10,0.6f);
	for (int i = 0;i<10; i++){
	    map.put(i,i+"");
	}
	for (int i = 0;i<10; i++){
	    System.out.println(map.get(i));
	}
    }

}
