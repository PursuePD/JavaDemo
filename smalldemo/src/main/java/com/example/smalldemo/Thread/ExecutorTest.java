package com.example.smalldemo.Thread;

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
public class ExecutorTest {

    public static void main(String[] args) throws  Exception{
        ExecutorServiceTest();
    }

    private static void ExecutorServiceTest() throws Exception{
        //创建一个线程池
//        ExecutorService pool = Executors.newFixedThreadPool(10);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().build();
        ExecutorService pool = new ThreadPoolExecutor(5,5,0L,TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());

        List<Future<String>> list = new ArrayList<>();

        for(int i = 0; i <10;i++){
            Callable c = () -> (int)(Math.random()*10000)+"time"+System.currentTimeMillis();
            // 执行任务并获取 Future 对象
            Future f = pool.submit(c);
            list.add(f);
        }

        // 关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
        for (Future<String> f : list) {
            // 从 Future 对象上获取任务的返回值，并输出到控制台
            System.out.println("res：" + f.get());
        }
    }
}
