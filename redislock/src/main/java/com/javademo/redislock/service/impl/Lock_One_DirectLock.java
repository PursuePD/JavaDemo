package com.javademo.redislock.service.impl;

import org.springframework.stereotype.Service;

/**
 * @Author:cuijialei
 * @Date: 2019/7/24
 * @Describe
 */
@Service("redisDirect")
public class Lock_One_DirectLock extends BaseRedisLock{

    /**
     * 直接调用setnx
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean lock(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }
}
