package com.example.statemachine.entity;

/**
 * @Author:cuijialei
 * @Date: 2019/5/13
 * @Describe
 */
public class Order {

    private String orderNO;
    private int status;

    public Order(String orderNO, int status) {
        this.orderNO = orderNO;
        this.status = status;
    }

    public Order() {
    }

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNO='" + orderNO + '\'' +
                ", status=" + status +
                '}';
    }
}
