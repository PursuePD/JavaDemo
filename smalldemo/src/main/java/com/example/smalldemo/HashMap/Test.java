package com.example.smalldemo.HashMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: 小崔
 * @create: 2019-11-26 11:27
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
	MyHashMap<String,String> map = new MyHashMap(10,0.6f);
//	for (int i = 0;i<10; i++){
//	    map.put(Math.random()*100 + "",i+"");
//	}

	for (int i = 0;i<10; i++){
	    map.put(i + "",i+"");
	}

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date nowDate = new Date();
	System.out.println(df.format(nowDate));
	Calendar cal= Calendar.getInstance();
	cal.setTime(nowDate);
	cal.add(Calendar.DATE,1);
	Date tomorrow = cal.getTime();
	System.out.println(df.format(tomorrow));
    }

}
