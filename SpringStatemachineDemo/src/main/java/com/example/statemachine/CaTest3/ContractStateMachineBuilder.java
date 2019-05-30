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
public class ContractStateMachineBuilder extends EnumStateMachineConfigurerAdapter<ContractState,ContractEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String CONTRACT_ORDER = "ContractOrderMachine";


    /**
     * 构建状态机
     *
     * @param beanFactory
     * @return
     * @throws Exception
     */
    public StateMachine<ContractState,ContractEvent> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<ContractState,ContractEvent> builder = StateMachineBuilder.builder();

        logger.info("构建合同状态机");

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(CONTRACT_ORDER)
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(ContractState.NullState)
                .states(EnumSet.allOf(ContractState.class));

        builder.configureTransitions()
                .withExternal()
                .source(ContractState.NullState).target(ContractState.ContractWaitConfirm)
                .event(ContractEvent.UploadContract)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ContractState.ContractWaitConfirm).target(ContractState.ContractExecuted)
                .event(ContractEvent.ConfirmContract)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ContractState.ContractWaitConfirm).target(ContractState.ContractExpired)
                .event(ContractEvent.InvalidateContract)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
                .and()
                .withExternal()
                .source(ContractState.ContractWaitConfirm).target(ContractState.ContractUnenforced)
                .event(ContractEvent.RejectContract)
                .action(stateContext -> {
                    logOrderInfo(stateContext);
                })
        ;


        return builder.build();
    }

    private void logOrderInfo(StateContext<ContractState,ContractEvent> stateContext) {
        logger.info("状态 {} -> {} ,动作 {}",stateContext.getSource().getId().getStateDesc(),stateContext.getTarget().getId().getStateDesc(),stateContext.getEvent().getEventDesc());
        Order msgOrder = stateContext.getMessage().getHeaders().get("order", Order.class);
        Order order = CaStateMachineController.orderMap.get(msgOrder.getOrderNO());
        order.setStatus(stateContext.getTarget().getId().getStateNo());
        logger.info("订单：order:{}",order );
    }

}
