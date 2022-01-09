package com.hit.logres.phoneproject.api.entity;

import java.io.Serializable;

public class NetCustomer implements Serializable {
    private Integer id; //ID
    private Integer customerId; //用户ID
    private String username; //用户名
    private String password; //密码

    public NetCustomer(Integer id, Integer customerId, String username, String password) {
        this.id = id;
        this.customerId = customerId;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NetCustomer() {
    }
}
