package com.example.statemachine.config;

import java.util.Arrays;

/**
 * @Author:cuijialei
 * @Date: 2019/5/13
 * @Describe
 */
public enum Events {

    START_PAYING(1,"点击支付操作"),
    CLOSE_ORDER(2,"关闭订单操作"),
    PAY_OVER(3,"支付成功操作"),
    PAY_FAILD(4,"支付失败操作"),

    ;


    private int EventNo;
    private String Event;

    Events(int eventNo, String event) {
        EventNo = eventNo;
        Event = event;
    }

    public static Events getEventByNo(int no){

        return (Events)(Arrays.stream(Events.values()).filter(e -> no == e.getEventNo()).toArray())[0];

//        for (Events events : Events.values()) {
//            if(events.getEventNo() == no ){
//                return events;
//            }
//        }
        //return null;
    }

    public int getEventNo() {
        return EventNo;
    }

    public void setEventNo(int eventNo) {
        EventNo = eventNo;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
