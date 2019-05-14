package com.example.statemachine.Test1;

import com.example.statemachine.config.Events;
import com.example.statemachine.config.States;
import com.example.statemachine.controller.SpringTestController;
import com.example.statemachine.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * @Author:cuijialei
 * @Date: 2019/5/13
 * @Describe
 */
@Component
public class OrderStateMachineBuilder extends EnumStateMachineConfigurerAdapter<States, Events> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String MACHINEID = "formMachine";

    /**
     * 构建状态机
     *
     * @param beanFactory
     * @return
     * @throws Exception
     */
    @Bean
    public StateMachine<States, Events> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

        logger.info("构建表单状态机");

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINEID)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(States.WAIT_PAY)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.WAIT_PAY).target(States.PAYING)
                .event(Events.START_PAYING)
                .action(stateContext -> {
                    Order msgOrder = stateContext.getMessage().getHeaders().get("order", Order.class);
                    Order order = SpringTestController.map.get(msgOrder.getOrderNO());
                    order.setStatus(States.PAYING.getStates());
                    logger.info("order:{}",order );
                })
                .and()
                .withExternal()
                .source(States.PAYING).target(States.OVER)
                .event(Events.PAY_OVER)
                .action(stateContext -> {
                    Order msgOrder = stateContext.getMessage().getHeaders().get("order", Order.class);
                    Order order = SpringTestController.map.get(msgOrder.getOrderNO());
                    order.setStatus(States.OVER.getStates());
                    logger.info("order:{}",order );
                })
                //.action(context -> logger.info("状态变为已完成：支付完成"))
                .and()
                .withExternal()
                .source(States.PAYING).target(States.PAY_FALID)
                .event(Events.PAY_FAILD)
                .action(new ComplexFormChoiceAction())
                //.action(context -> logger.info("状态变为支付失败：支付失败"))
                .and()
                .withExternal()
                .source(States.WAIT_PAY).target(States.CLOSE)
                .event(Events.CLOSE_ORDER)
                .action(new ComplexFormChoiceAction())
               // .action(context -> logger.info("状态变为已关闭：关闭订单"))
        ;


        return builder.build();
    }
}
