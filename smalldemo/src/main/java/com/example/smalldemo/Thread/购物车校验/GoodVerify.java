package com.example.smalldemo.Thread.购物车校验;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author: 小崔
 * @create: 2019-11-15 16:28
 * @Description:
 */
public class GoodVerify implements Callable<Boolean> {

    private String goodName;

    /**
     * 商品数量
     */
    private int goodCount;

    public GoodVerify(String goodName, int goodCount) {
	this.goodName = goodName;
	this.goodCount = goodCount;
    }

    @Override
    public Boolean call() throws Exception {
	System.out.println(getTime()+":商品"+goodName+"执行校验");
	Thread.sleep(200); //模拟耗时
	if(goodCount <= 0){
	    System.out.println(getTime()+":商品"+goodName+"数量小于0 校验失败");
	    return false;
	}
	System.out.println(getTime()+":商品"+goodName+"校验成功");
	return true;
    }

    private String getTime(){
        Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	return dateFormat.format(now);
    }
}
