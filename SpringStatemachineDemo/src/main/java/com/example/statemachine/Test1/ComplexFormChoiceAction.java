package com.example.statemachine.Test1;

import com.example.statemachine.config.Events;
import com.example.statemachine.config.States;
import com.example.statemachine.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @Author:cuijialei
 * @Date: 2019/5/13
 * @Describe
 */
public class ComplexFormChoiceAction implements Action<States, Events> {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void execute(StateContext<States, Events> context) {
        Order order = context.getMessage().getHeaders().get("order", Order.class);
        logger.info("order:{}",order );
    }
}
