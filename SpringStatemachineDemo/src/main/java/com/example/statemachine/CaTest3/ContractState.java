package com.example.statemachine.CaTest3;



/**
 * @Author:cuijialei
 * @Date: 2019/5/20
 * @Describe
 */
public enum ContractState {
    //等待确认	已生效	未生效	已失效
    NullState("无",0),
    ContractWaitConfirm("等待确认合同",2001),
    ContractExecuted("已生效合同",2002),
    ContractUnenforced("未生效合同",2003),
    ContractExpired("已失效合同",2004),

    ;

    private String StateDesc;
    private int StateNo;

    ContractState(String stateDesc, int stateNo) {
        StateDesc = stateDesc;
        StateNo = stateNo;
    }

    public static ContractState getExtraStateByNo(int StateNo){
        ContractState.values();
        //return (ExtraState)(Arrays.stream(ExtraState.values()).filter(e -> StateNo == e.getStateNo()).toArray())[0];
        for (ContractState extraState : ContractState.values()) {
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
