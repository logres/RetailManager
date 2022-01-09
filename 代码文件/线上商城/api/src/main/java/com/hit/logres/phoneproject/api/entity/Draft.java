package com.hit.logres.phoneproject.api.entity;

import java.io.Serializable;

public class Draft implements Serializable {
    Integer id;	//草稿ID
    Integer customerId;	//顾客ID
    Integer userId;	//员工ID
    String status; //状态

    public Draft(Integer id, Integer customerId, Integer userId, String status) {
        this.id = id;
        this.customerId = customerId;
        this.userId = userId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Draft() {
    }
}
