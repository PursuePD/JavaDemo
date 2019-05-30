package com.example.statemachine.CaTest3;

import com.example.statemachine.Test1.ComplexFormChoiceAction;
import com.example.statemachine.config.Events;
import com.example.statemachine.config.States;
import com.example.statemachine.controller.CaStateMachineController;
import com.example.statemachine.controller.SpringTestController;
import com.example.statemachine.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
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
public class ExtraOrderStateMachineBuilder extends EnumStateMachineConfigurerAdapter<ExtraState, ExtraEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public final static String EXTRA_ORDER = "ExtraOrderMachine";


    /**
     * 构建状态机
     *
     * @param beanFactory
     * @return
     * @throws Exception
     */
    public StateMachine<ExtraState, ExtraEvent> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<ExtraState, ExtraEvent> builder = StateMachineBuilder.builder();

        logger.info("构建增值服务状态机");

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(EXTRA_ORDER)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(ExtraState.NullState)
                .states(EnumSet.allOf(ExtraState.class));

        builder.configureTransitions()
                .withExternal()
                .source(ExtraState.NullState).target(ExtraState.WaitPay)
                .event(ExtraEvent.Order)
                .action(stateContext -> {
                    logger.info("创建订单");
                    Order msgOrder = stateContext.getMessage().getHeaders().get("order", Order.class);
                    Order newOrder = new Order(msgOrder.getOrderNO(), ExtraState.WaitPay.getStateNo());
                    CaStateMachineController.orderMap.put(msgOrder.getOrderNO(), newOrder);
                })
                .and()
                .withExternal()
                .source(ExtraState.WaitPay).target(ExtraState.Paying)
                .event(ExtraEvent.Pay)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ExtraState.Paying).target(ExtraState.OrderSuccess)
                .event(ExtraEvent.PaySuccessNotice)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ExtraState.Paying).target(ExtraState.OrderClose)
                .event(ExtraEvent.PayFailedNotice)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ExtraState.WaitPay).target(ExtraState.OrderClose)
                .event(ExtraEvent.OrderTimeOut)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ExtraState.Paying).target(ExtraState.OrderClose)
                .event(ExtraEvent.OrderTimeOut)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ExtraState.WaitPay).target(ExtraState.OrderClose)
                .event(ExtraEvent.CloseOrder)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ExtraState.Paying).target(ExtraState.OrderClose)
                .event(ExtraEvent.CloseOrder)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ExtraState.OrderClose).target(ExtraState.OrderError)
                .event(ExtraEvent.PaySuccessNotice)
                .action(new ComplexFormChoiceAction())
        ;


        return builder.build();
    }

    private void logOrderInfo(StateContext<ExtraState, ExtraEvent> stateContext) {
        logger.info("状态 {} -> {} ,动作 {}",stateContext.getSource().getId().getStateDesc(),stateContext.getTarget().getId().getStateDesc(),stateContext.getEvent().getEventDesc());
        Order msgOrder = stateContext.getMessage().getHeaders().get("order", Order.class);
        Order order = CaStateMachineController.orderMap.get(msgOrder.getOrderNO());
        order.setStatus(stateContext.getTarget().getId().getStateNo());
        logger.info("订单：order:{}",order );
    }

    public class ComplexFormChoiceAction implements Action<ExtraState, ExtraEvent> {
        @Override
        public void execute(StateContext<ExtraState, ExtraEvent> context) {
            logger.info("状态:{}->{} ,动作:{}",ExtraState.Paying.getStateDesc(),ExtraState.OrderClose.getStateDesc(),ExtraEvent.CloseOrder.getEventDesc());
            Order msgOrder = context.getMessage().getHeaders().get("order", Order.class);
            logger.info("订单：order:{}",msgOrder );
        }


    }


}
