package com.example.springtest.service.impl;

import com.example.springtest.service.LoggerService;
import com.example.springtest.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author:cuijialei
 * @Date: 2019/4/28
 * @Describe
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerServiceImpl.class);

    @Override
    public void printInfo(String info) {
        LOGGER.info("{}打印消息：{}",DateUtils.getStringTime(System.currentTimeMillis()), info);
    }
}
