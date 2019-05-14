package com.example.statemachine.Test1;//package com.example.statemachine.config;
//
//import com.example.statemachine.entity.Order;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.Message;
//import org.springframework.statemachine.action.Action;
//import org.springframework.statemachine.annotation.OnTransition;
//import org.springframework.statemachine.config.EnableStateMachine;
//import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
//import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
//import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
//import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
//import org.springframework.statemachine.listener.StateMachineListener;
//import org.springframework.statemachine.listener.StateMachineListenerAdapter;
//import org.springframework.statemachine.transition.Transition;
//
//import java.util.EnumSet;
//
///**
// * @Author:cuijialei
// * @Date: 2019/5/13
// * @Describe
// */
//@Configuration
//@EnableStateMachine
//public class SpringStatemachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//
//    @Override
//    public void configure(StateMachineStateConfigurer<States, Events> states)
//            throws Exception {
//        states
//                .withStates()
//                .initial(States.WAIT_PAY)
//                .states(EnumSet.allOf(States.class));
//
//    }
//
//
//    @Override
//    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
//            throws Exception {
//        transitions
//                .withExternal()
//                .source(States.WAIT_PAY).target(States.PAYING)
//                .event(Events.START_PAYING)
//                .action(new ComplexFormChoiceAction(),new ComplexFormChoiceAction())
//                .and()
//                .withExternal()
//                .source(States.PAYING).target(States.OVER)
//                .event(Events.PAY_OVER)
//                .action(context -> logger.info("状态变为已完成：支付完成"))
//                .and()
//                .withExternal()
//                .source(States.PAYING).target(States.PAY_FALID)
//                .event(Events.PAY_FAILD)
//                .action(context -> logger.info("状态变为支付失败：支付失败"))
//                .and()
//                .withExternal()
//                .source(States.WAIT_PAY).target(States.CLOSE)
//                .event(Events.CLOSE_ORDER)
//                .action(context -> logger.info("状态变为已关闭：关闭订单"))
//        ;
//    }
//
//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
//            throws Exception {
//        config
//                .withConfiguration()
//                .machineId("orderMachine")
//                .listener(listener());
//    }
//    @Bean
//    public StateMachineListener<States, Events> listener() {
//        return new StateMachineListenerAdapter<States, Events>() {
//            @Override
//            public void transition(Transition<States, Events> transition) {
//                if(transition.getTarget().getId() == States.WAIT_PAY) {
//                    logger.info("监听：订单创建，待支付");
//                    return;
//                }
////                if(transition.getSource().getId() == States.UNPAID
////                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
////                    logger.info("用户完成支付，待收货");
////                    return;
////                }
////                if(transition.getSource().getId() == States.WAITING_FOR_RECEIVE
////                        && transition.getTarget().getId() == States.DONE) {
////                    logger.info("用户已收货，订单完成");
////                    return;
////                }
//            }
//        };
//    }
//
//
//    public Action<States, Events> clickPay() {
//        return context -> logger.info("状态变为支付中：点击支付操作" );
//    }
//
//    @OnTransition(source = "WAIT_PAY", target = "PAYING")
//    public void receive(Message<Events> message) {
//        logger.info("传递的参数：" + message.getHeaders().get("order"));
//    }
//
////    public StateMachine<States, Events> buildMachine() throws Exception {
////        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();
////
////        builder.configureStates()
////                .withStates()
////                .initial(States.WAIT_PAY)
////                .states(EnumSet.allOf(States.class));
////
////        builder.configureTransitions()
////                .withExternal()
////                .source(States.WAIT_PAY).target(States.PAYING)
////                .event(Events.START_PAYING)
////                .action(clickPay())
////                .and()
////                .withExternal()
////                .source(States.PAYING).target(States.OVER)
////                .event(Events.PAY_OVER)
////                .action(context -> logger.info("状态变为已完成：支付完成"))
////                .and()
////                .withExternal()
////                .source(States.PAYING).target(States.PAY_FALID)
////                .event(Events.PAY_FAILD)
////                .action(context -> logger.info("状态变为支付失败：支付失败"))
////                .and()
////                .withExternal()
////                .source(States.WAIT_PAY).target(States.CLOSE)
////                .event(Events.CLOSE_ORDER)
////                .action(context -> logger.info("状态变为已关闭：关闭订单"))
////        ;
////
////        return builder.build();
////    }
//
//
//}
