package com.example.springtest.controller;

import com.example.springtest.service.TimeWheelService;
import com.example.springtest.util.DateUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
@RestController
public class SpringTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringTestController.class);

    @Autowired
    private TimeWheelService timeWheelService;

    @GetMapping("/timeWheelTest/{seconds}")
    @ApiImplicitParam(paramType = "path",dataType = "int",name = "seconds",value = "延时的时间",required = true )
    public String timeWheelTest(@PathVariable int seconds){
        LOGGER.info("定时任务当前时间：{} ， 任务执行时间：{}",DateUtils.getStringTime(System.currentTimeMillis()),seconds+"秒");
        timeWheelService.addTimeTaskNewOrder(seconds,TimeUnit.MINUTES,"");
        return "";
    }

}
