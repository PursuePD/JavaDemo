package com.example.smalldemo.Thread.购物车校验;

/**
 * @author: 小崔
 * @create: 2019-11-15 16:35
 * @Description:
 */
public class Good {
    private String goodName;

    /**
     * 商品数量
     */
    private int goodCount;

    public Good(String goodName, int goodCount) {
	this.goodName = goodName;
	this.goodCount = goodCount;
    }

    public Good() {
    }

    public String getGoodName() {
	return goodName;
    }

    public void setGoodName(String goodName) {
	this.goodName = goodName;
    }

    public int getGoodCount() {
	return goodCount;
    }

    public void setGoodCount(int goodCount) {
	this.goodCount = goodCount;
    }
}
