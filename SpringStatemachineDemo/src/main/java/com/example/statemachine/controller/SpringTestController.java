package com.example.statemachine.controller;

import com.example.statemachine.Test1.OrderStateMachineBuilder;
import com.example.statemachine.config.Events;
import com.example.statemachine.config.States;
import com.example.statemachine.entity.Order;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:cuijialei
 * @Date: 2018/7/31
 * @Describe
 */
@RestController
public class SpringTestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final Map<String,Order> map = new HashMap<>(16);

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Autowired
    private OrderStateMachineBuilder orderStateMachineBuilder;

    @Autowired
    private BeanFactory beanFactory;



    @GetMapping("/push/{event}/{orderNo}")
    @ApiOperation(value = "user",notes = "无")
    public void findBy(@PathVariable("event") int event,@PathVariable("orderNo") String orderNo)throws Exception {
        //StateMachine<States, Events> stateMachine = orderStateMachineBuilder.build(beanFactory);

        Order order = map.get(orderNo);
        if (ObjectUtils.isEmpty(order)) {
            logger.info("创建订单");
            Order newOrder = new Order(orderNo, States.WAIT_PAY.getStates());
            map.put(orderNo, newOrder);
        } else {
            stateMachine.stop();
            List<StateMachineAccess<States, Events>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
            for (StateMachineAccess<States, Events> a : withAllRegions) {
                a.resetStateMachine(new DefaultStateMachineContext<States, Events>(States.getStates(order.getStatus()), null, null, null));
            }
            stateMachine.start();
            Message<Events> message = MessageBuilder.withPayload(Events.getEventByNo(event)).setHeader("order", order).build();
            stateMachine.sendEvent(message);
        }
    }

    @GetMapping("/push/test")
    @ApiOperation(value = "user",notes = "无")
    public void Test()throws Exception{
        StateMachine<States, Events> stateMachine = orderStateMachineBuilder.build(beanFactory);
        System.out.println(stateMachine.getId());

        // 创建流程
        stateMachine.start();

        stateMachine.sendEvent(Events.START_PAYING);

        stateMachine.sendEvent(Events.PAY_OVER);

        // 获取最终状态
        System.out.println("最终状态：" + stateMachine.getState().getId());
    }

    @GetMapping("/push/Complextest")
    @ApiOperation(value = "user",notes = "无")
    public void ComplexTest()throws Exception{
        StateMachine<States, Events> stateMachine = orderStateMachineBuilder.build(beanFactory);
        System.out.println(stateMachine.getId());

        // 创建流程
        stateMachine.start();

        stateMachine.sendEvent(Events.START_PAYING);

        stateMachine.sendEvent(Events.PAY_OVER);

        // 获取最终状态
        System.out.println("最终状态：" + stateMachine.getState().getId());
    }


}
