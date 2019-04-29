package com.example.springtest.config;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetAddress;


/**
 * Created by cjl on 2018/7/9.
 */
@Component
public class CommonConfig {
    static Logger logger = LoggerFactory.getLogger(CommonConfig.class);

    //服务标识
    //@Value("${service-symbol}")
    public String SERVICE_SYMBOL = "DelayedTest";

    @Value("${server.port}")
    public String PORT;

    @Value("${spring.application.name}")
    public String APPLICATION_NAME;

    public int INDEX = 99;

    public int INCREASE = 0;

    public int REFUND_INCREASE = 0;

    @Autowired
    RedissonClient redissonClient;

    @Bean
    public int setINDEX(){
        RMap<String,Integer> map = redissonClient.getMap("workIndexMap");

        String hostName = "";
        String ip = "";
        try{
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress().toString(); //获取本机ip
            hostName = addr.getHostName().toString(); //获取本机计算机名称
            logger.info("本机IP："+ip+"\n本机名称:"+hostName);
        }catch(Exception e){
            e.printStackTrace();
        }
        //服务名称
        String serviceNameSpace = SERVICE_SYMBOL + ":" + APPLICATION_NAME;
        //机器名称 + ip + 服务名称 + 端口号
        String mac = hostName+ ":" + ip + ":" + serviceNameSpace + ":" + PORT;
        Integer mymac = map.get(mac);
        logger.info("mac:"+mac);
        if(mymac == null){
            RAtomicLong rAtomicLong = redissonClient.getAtomicLong(serviceNameSpace);
            int num = (int)rAtomicLong.getAndIncrement();
            map.put(mac,num);
            return this.INDEX = num;
        }
        return this.INDEX = mymac;
    }

    //业务号+服务唯一编号+时间戳+自增数
    public synchronized String getRefundOrderSn(){
        String sn = "010" + String.format("%0"+2+"d", INDEX) + System.currentTimeMillis() + String.format("%0"+3+"d", REFUND_INCREASE);
        if (REFUND_INCREASE < 999) {
            ++REFUND_INCREASE;
        } else {
            REFUND_INCREASE = 0;
        }
        return sn;
    }
}
