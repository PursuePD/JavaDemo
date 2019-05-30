package com.example.statemachine.CaTest3;


import java.util.Arrays;

/**
 * @Author:cuijialei
 * @Date: 2019/5/20
 * @Describe
 */
public enum OfflineTradeEvent {
    //主流程
    StartOrder("确认订单",1101),
    ConfirmOrder("确认订单",1102),
    UploadPayInvoice("上传支付凭证",1103),
    ConfirmPayInvoice("确认支付凭证",1104),
    DeliverGoods("发货（上传三单）",1105),
    ConfirmDeliver("确认收货",1106),
    ConfirmDraftOrder("确认草拟订单",1107),
    CloseOrder("关闭订单",1108),

    ;

    private String EventDesc;
    private int EventNo;

    OfflineTradeEvent(String eventDesc, int eventNo) {
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
