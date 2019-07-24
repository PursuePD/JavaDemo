package com.javademo.redislock.service.impl;

import com.javademo.redislock.service.MyDistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author:cuijialei
 * @Date: 2019/7/24
 * @Describe
 */
@Service
public abstract class BaseRedisLock implements MyDistributedLock {
    @Autowired
    public StringRedisTemplate redisTemplate;

    @Override
    public boolean unlock(String key, String value) {
        return redisTemplate.delete(key);
    }
}
