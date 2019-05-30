package com.example.statemachine.CaTest3;

import com.example.statemachine.controller.CaStateMachineController;
import com.example.statemachine.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
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
public class OfflineTradeMachineBuilder extends EnumStateMachineConfigurerAdapter<OfflineTradeState,OfflineTradeEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String OFFLINE_TRADE_ORDER = "OfflineTradeMachine";


    /**
     * 构建状态机
     *
     * @param beanFactory
     * @return
     * @throws Exception
     */
    public StateMachine<OfflineTradeState,OfflineTradeEvent> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<OfflineTradeState,OfflineTradeEvent> builder = StateMachineBuilder.builder();

        logger.info("构建TT订单状态机");

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(OFFLINE_TRADE_ORDER)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(OfflineTradeState.NullState)
                .states(EnumSet.allOf(OfflineTradeState.class));

        builder.configureTransitions()
                .withExternal()
                .source(OfflineTradeState.NullState).target(OfflineTradeState.WaitSellerConfirm)
                .event(OfflineTradeEvent.StartOrder)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })

        ;


        return builder.build();
    }

    private void logOrderInfo(StateContext<OfflineTradeState,OfflineTradeEvent> stateContext) {
        logger.info("状态 {} -> {} ,动作 {}",stateContext.getSource().getId().getStateDesc(),stateContext.getTarget().getId().getStateDesc(),stateContext.getEvent().getEventDesc());
        Order msgOrder = stateContext.getMessage().getHeaders().get("order", Order.class);
        Order order = CaStateMachineController.orderMap.get(msgOrder.getOrderNO());
        order.setStatus(stateContext.getTarget().getId().getStateNo());
        logger.info("订单：order:{}",order );
    }

}
