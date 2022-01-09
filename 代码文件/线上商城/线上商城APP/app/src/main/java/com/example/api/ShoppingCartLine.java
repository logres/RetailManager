package com.example.api;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShoppingCartLine implements Serializable {
    Integer id;	//订单项ID
    Integer shoppingCartId;	//购物车
    Integer goodId;	//商品ID
    BigDecimal unitPrice;	//单价
    Integer amount;	//数量

    public ShoppingCartLine(Integer id, Integer shoppingCartId, Integer goodId, BigDecimal unitPrice, Integer amount) {
        this.id = id;
        this.shoppingCartId = shoppingCartId;
        this.goodId = goodId;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
