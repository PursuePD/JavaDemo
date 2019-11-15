package com.example.smalldemo.Thread.购物车校验;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author:cuijialei
 * @Date: 2019/7/4
 * @Describe
 * 思考应用：比如在购物车下单时，对所有的商品进行校验，判断是否都能下单，库存问题等。
 */
public class ShoppingCartTest {

    public static void main(String[] args) {
	List<Good> goodList = new ArrayList<>();
	goodList.add(new Good("巧克力A",11));
	goodList.add(new Good("巧克力B",10));
	goodList.add(new Good("巧克力C",11));
	goodList.add(new Good("巧克力D",12));
	goodList.add(new Good("巧克力E",0));

	if(shoppingCartVerify(goodList)){
	    System.out.println("该购物车商品校验通过");
	}else{
	    System.out.println("该购物车商品校验失败");
	}

	shoppingCartVerifySingle(goodList);
    }

    public static boolean shoppingCartVerify(List<Good> goodList){
        long time1 = System.currentTimeMillis();
	ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().build();
	ExecutorService pool = new ThreadPoolExecutor(5,200,0L, TimeUnit.MILLISECONDS,
		new LinkedBlockingQueue<Runnable>(1024),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());

	List<Future<Boolean>> list = new ArrayList<>();

	for (Good good : goodList) {
	    list.add(pool.submit(new GoodVerify(good.getGoodName(),good.getGoodCount())));
	}

	boolean result = true;
	for (Future<Boolean> booleanFuture : list) {
	    try {
	        if(!booleanFuture.get()){
		    result = false;
		    break;
		}
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    } catch (ExecutionException e) {
		e.printStackTrace();
	    }
	}
	System.out.println("多线程校验耗时："+(System.currentTimeMillis() - time1));
	return result;
    }

    public static boolean shoppingCartVerifySingle(List<Good> goodList){
	long time1 = System.currentTimeMillis();
	boolean result = true;
	for (Good good : goodList) {
	    try {
		Thread.sleep(200); //模拟耗时
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    if(good.getGoodCount() <= 0){
		result = false;
	    }
	}
	System.out.println("普通校验耗时："+(System.currentTimeMillis() - time1));
	return result;
    }
}
