package com.example.statemachine.CaTest3;

import java.util.Arrays;

/**
 * @Author:cuijialei
 * @Date: 2019/5/20
 * @Describe
 */
public enum ExtraEvent {
    //下单
    Order("下单",1),
    //支付
    Pay("支付",2),
    //支付成功
    PaySuccessNotice("支付成功通知",3),
    //支付失败
    PayFailedNotice("支付失败通知",4),
    //订单超时
    OrderTimeOut("订单超时",5),

    CloseOrder("用户关闭订单",6),
    ;

    private String EventDesc;
    private int EventNo;

    ExtraEvent(String eventDesc, int eventNo) {
        EventDesc = eventDesc;
        EventNo = eventNo;
    }

    public static ExtraEvent getExtraEventByNo(int eventNo){
        return (ExtraEvent)(Arrays.stream(ExtraEvent.values()).filter(extraEvent -> eventNo == extraEvent.getEventNo()).toArray())[0];
    }

    public String getEventDesc() {
        return EventDesc;
    }

    public void setEventDesc(String eventDesc) {
        EventDesc = eventDesc;
    }

    public int getEventNo() {
        return EventNo;
    }

    public void setEventNo(int eventNo) {
        EventNo = eventNo;
    }
}
