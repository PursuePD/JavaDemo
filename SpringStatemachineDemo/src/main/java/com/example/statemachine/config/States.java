package com.example.statemachine.config;

import java.util.Arrays;

/**
 * @Author:cuijialei
 * @Date: 2019/5/13
 * @Describe
 */
public enum States {

    WAIT_PAY((byte)1,"待支付"),
    PAYING((byte)2,"支付中"),
    OVER((byte)3,"已完成"),
    CLOSE((byte)4,"已关闭"),
    PAY_FALID((byte)5,"支付失败"),
    ;


    private byte States;
    private String StatesDes;

    States(byte states, String statesDes) {
        States = states;
        StatesDes = statesDes;
    }

    public static States getStates(int states){
        return (States)(Arrays.stream(com.example.statemachine.config.States.values()).filter(e -> states == e.getStates()).toArray())[0];
    }

    public byte getStates() {
        return States;
    }

    public void setStates(byte states) {
        States = states;
    }

    public String getStatesDes() {
        return StatesDes;
    }

    public void setStatesDes(String statesDes) {
        StatesDes = statesDes;
    }
}
