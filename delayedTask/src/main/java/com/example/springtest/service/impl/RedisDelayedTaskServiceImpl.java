package com.example.springtest.service.impl;

import com.example.springtest.service.RedisDelayedTaskService;
import com.example.springtest.util.DateUtils;

import org.redisson.api.*;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryExpiredListener;
import org.redisson.api.map.event.EntryRemovedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @Author:cuijialei
 * @Date: 2019/4/29
 * @Describe
 */
@Service
public class RedisDelayedTaskServiceImpl implements RedisDelayedTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDelayedTaskServiceImpl.class);

    @Autowired
    RedissonClient redissonClient;

    @Override
    public void addTask(long overTime, String json) {
       RScoredSortedSet<String> rScoredSortedSet = redissonClient.getScoredSortedSet("DelayedTask");
        rScoredSortedSet.add(overTime,json);
    }

    @Override
    public String getFirstReadyTaskAndRemove() {
        RScoredSortedSet<String> rScoredSortedSet = redissonClient.getScoredSortedSet("DelayedTask");
        String json = rScoredSortedSet.first();
        if(StringUtils.isEmpty(json)){
            return null;
        }
        double score = rScoredSortedSet.firstScore();
        if(DateUtils.getLongTime(System.currentTimeMillis()) < score){
            return null;
        }
        if(rScoredSortedSet.remove(json)){
            return json;
        }else{
            return null;
        }
    }

    /**
     * 获取可以执行的任务集合
     * @return
     */
    @Override
    public Collection<String> getToDoRangeTask(long endTime) {
        RScoredSortedSet<String> rScoredSortedSet = redissonClient.getScoredSortedSet("DelayedTask");
        Collection<String> collection = rScoredSortedSet.valueRange( 0 ,true,endTime,true);
        return collection;
    }

    @Override
    public boolean remove(String json) {
        RScoredSortedSet<String> rScoredSortedSet = redissonClient.getScoredSortedSet("DelayedTask");
        if(rScoredSortedSet.remove(json)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void addOverdueTask(long overdueTime,String key,String json) {
        long seconds = (overdueTime - System.currentTimeMillis())/1000;
        RBucket rBucket = redissonClient.getBucket(key);
        rBucket.set(json,seconds,TimeUnit.SECONDS);

    }

}
