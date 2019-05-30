package com.example.statemachine.CaTest3;


import java.util.Arrays;

/**
 * @Author:cuijialei
 * @Date: 2019/5/20
 * @Describe
 */
public enum ContractEvent {
    //合同
    UploadContract("上传合同",2001),
    ConfirmContract("确认合同",2002),
    RejectContract("拒绝合同",2003),
    InvalidateContract("修改订单使合同失效",2004),

    ;

    private String EventDesc;
    private int EventNo;

    ContractEvent(String eventDesc, int eventNo) {
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
