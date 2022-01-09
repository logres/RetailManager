package com.example.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Invoice implements Serializable {
    Integer id;	//订单ID
    Integer customerId;	//顾客ID
    Timestamp time;	//时间戳
    BigDecimal totalPrice;	//总价
    String status;	//订单状态
    BigDecimal grossMargin;	//毛利润
    Integer userId;	//员工ID

    public Invoice(Integer id, Integer customerId, Timestamp time, BigDecimal totalPrice, String status, BigDecimal grossMargin, Integer userId) {
        this.id = id;
        this.customerId = customerId;
        this.time = time;
        this.totalPrice = totalPrice;
        this.status = status;
        this.grossMargin = grossMargin;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(BigDecimal grossMargin) {
        this.grossMargin = grossMargin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
