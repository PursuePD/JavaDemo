package com.javademo.redislock.service;

/**
 * @Author:cuijialei
 * @Date: 2019/7/24
 * @Describe
 */
public interface MyDistributedLock {
    boolean lock(String key, String value);
    boolean unlock(String key, String value);
}
