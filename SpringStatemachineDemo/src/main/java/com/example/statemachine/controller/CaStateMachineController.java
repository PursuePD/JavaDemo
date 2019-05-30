package com.example.statemachine.controller;

import com.example.statemachine.CaTest3.ExtraEvent;
import com.example.statemachine.CaTest3.ExtraState;
import com.example.statemachine.CaTest3.StateMachineBulider;
import com.example.statemachine.entity.Order;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:cuijialei
 * @Date: 2019/5/21
 * @Describe
 */
@RestController
public class CaStateMachineController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final Map<String,Order> orderMap = new HashMap<>(16);

    @GetMapping("/StateMachine/newOrder/{orderNo}")
    @ApiOperation(value = "user",notes = "无")
    public void newOrder(@PathVariable("orderNo") String orderNo)throws Exception {
        logger.info("创建订单");
        Order newOrder = new Order(orderNo, ExtraState.WaitPay.getStateNo());
        orderMap.put(orderNo, newOrder);
    }

    @GetMapping("/StateMachine/{event}/{orderNo}")
    @ApiOperation(value = "user",notes = "无")
    public void findBy(@PathVariable("event") int event, @PathVariable("orderNo") String orderNo)throws Exception {
        StateMachine<ExtraState, ExtraEvent>  stateMachine = StateMachineBulider.bulider(1);

        Order order = orderMap.get(orderNo);
        if(order == null){
            order = new Order();
            order.setOrderNO(orderNo);
        }

        stateMachine.start();
        List<StateMachineAccess<ExtraState, ExtraEvent>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
        logger.info("withAllRegions size:{}",withAllRegions.size());
        for (StateMachineAccess<ExtraState, ExtraEvent> a : withAllRegions) {
            a.resetStateMachine(new DefaultStateMachineContext<>(ExtraState.getExtraStateByNo(order.getStatus()), null, null, null));
        }
        Message<ExtraEvent> message = MessageBuilder.withPayload(ExtraEvent.getExtraEventByNo(event)).setHeader("order", order).build();
        stateMachine.sendEvent(message);
        stateMachine.stop();
    }


    @GetMapping("/StateMachine/orderInfo/{orderNo}")
    @ApiOperation(value = "user",notes = "无")
    public void orderInfo(@PathVariable("orderNo") String orderNo)throws Exception {
        logger.info("订单：{}",orderMap.get(orderNo) );
    }



    @GetMapping("/Test")
    public void orderInfo1()throws Exception {
        StateMachine<ExtraState, ExtraEvent>  stateMachine = StateMachineBulider.bulider(1);
        // 创建流程
        stateMachine.start();

        // 触发PAY事件
        stateMachine.sendEvent(ExtraEvent.Order);

        System.out.println("id：" + stateMachine.getId());

        // 获取最终状态
        System.out.println("最终状态：" + stateMachine.getState().getId());
    }
}
