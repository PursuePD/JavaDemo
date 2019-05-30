package com.example.statemachine.CaTest3;



/**
 * @Author:cuijialei
 * @Date: 2019/5/20
 * @Describe
 */
public enum OfflineTradeState {
    //待卖方确认	待买方确认	签约中	待付预付款	待确认预付款	待发货	待付款	待收款	待收货	已完成	已关闭 草拟中
    NullState("无",0),
    WaitSellerConfirm("待卖方确认",1101),
    WaitBuyerConfirm("待买方确认",1102),
    InTheContract("签约中",1103),
    WaitPayFirst("待付预付款",1104),
    WaitConfirmFirst("待确认预付款",1105),
    WaitDeliverGoods("待发货",1106),
    WaitPayFinal("待付款",1107),
    WaitConfirmFinal("待收款",1108),
    WaitTakeGoods("待收货",1109),
    OrderSucceed("已完成",1110),
    OrderClose("已关闭",1111),
    DraftOrder("草拟中",1112),

    ;

    private String StateDesc;
    private int StateNo;

    OfflineTradeState(String stateDesc, int stateNo) {
        StateDesc = stateDesc;
        StateNo = stateNo;
    }

    public static OfflineTradeState getExtraStateByNo(int StateNo){
        OfflineTradeState.values();
        //return (ExtraState)(Arrays.stream(ExtraState.values()).filter(e -> StateNo == e.getStateNo()).toArray())[0];
        for (OfflineTradeState extraState : OfflineTradeState.values()) {
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
