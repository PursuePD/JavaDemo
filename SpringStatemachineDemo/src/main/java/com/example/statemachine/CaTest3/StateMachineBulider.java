package com.example.statemachine.CaTest3;

import com.example.statemachine.config.Events;
import com.example.statemachine.config.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @Author:cuijialei
 * @Date: 2019/5/21
 * @Describe
 */
@Component
public class StateMachineBulider {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BeanFactory beanFactory;

    private static ExtraOrderStateMachineBuilder extraOrderStateMachineBuilder ;

    private static StateMachine<ExtraState, ExtraEvent> extraStateMachine;

    StateMachineBulider()throws Exception{
        if(ObjectUtils.isEmpty(extraOrderStateMachineBuilder)){
            extraOrderStateMachineBuilder = new ExtraOrderStateMachineBuilder();
        }
        extraStateMachine = extraOrderStateMachineBuilder.build(beanFactory);
    }


    public static StateMachine bulider(int orderType){
        return extraStateMachine;
    }

}
