package com.example.statemachine.Test1;

import com.example.statemachine.config.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * @Author:cuijialei
 * @Date: 2019/5/13
 * @Describe
 */
@Component
@WithStateMachine(id="formMachine")
public class OrderEventConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 当前状态BLANK_FORM
     */
    @OnTransition(target = "WAIT_PAY")
    public void create() {
        logger.info("---创建订单---");
    }

    /**
     * WAIT_PAY->PAYING 执行的动作
     */
    @OnTransition(source = "WAIT_PAY", target = "PAYING")
    public void write(Message<Events> message) {
        logger.info("传递的参数：" + message.getHeaders().get("order"));
        logger.info("---填写完表单---");
    }

    /**
     * PAYING->OVER 执行的动作
     */
    @OnTransition(source = "PAYING", target = "OVER")
    public void confirm(Message<Events> message) {
        logger.info("传递的参数：" + message.getHeaders().get("order"));
        logger.info("---状态变为已完成：支付完成---");
    }

    /**
     * WAIT_PAY->CLOSE 执行的动作
     */
    @OnTransition(source = "WAIT_PAY", target = "CLOSE")
    public void submit(Message<Events> message) {
        logger.info("传递的参数：" + message.getHeaders().get("order"));
        logger.info("---状态变为已关闭：关闭订单---");
    }
}
