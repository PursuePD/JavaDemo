package com.example.statemachine.CaTest3;



/**
 * @Author:cuijialei
 * @Date: 2019/5/20
 * @Describe
 */
public enum ExtraState {
    //待付款	支付中	已完成	已关闭	支付异常
    WaitPay("待付款",1),
    Paying("支付中",2),
    OrderSuccess("已完成",3),
    OrderClose("已关闭",4),
    OrderError("支付异常",5),

    ;

    private String StateDesc;
    private int StateNo;

    ExtraState(String stateDesc, int stateNo) {
        StateDesc = stateDesc;
        StateNo = stateNo;
    }

    public static ExtraState getExtraStateByNo(int StateNo){
        ExtraState.values();
        //return (ExtraState)(Arrays.stream(ExtraState.values()).filter(e -> StateNo == e.getStateNo()).toArray())[0];
        for (ExtraState extraState : ExtraState.values()) {
            if(extraState.StateNo == StateNo){
                return extraState;
            }
        }
        return null;
    }

    public String getStateDesc() {
        return StateDesc;
    }

    public void setStateDesc(String stateDesc) {
        StateDesc = stateDesc;
    }

    public int getStateNo() {
        return StateNo;
    }

    public void setStateNo(int stateNo) {
        StateNo = stateNo;
    }
}
