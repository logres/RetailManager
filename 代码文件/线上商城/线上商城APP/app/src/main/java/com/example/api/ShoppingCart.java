package com.example.api;

import java.io.Serializable;

public class ShoppingCart implements Serializable {
    private Integer id; //ID
    private Integer netCustomerId; //ID

    public ShoppingCart(Integer id, Integer netCustomerId) {
        this.id = id;
        this.netCustomerId = netCustomerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNetCustomerId() {
        return netCustomerId;
    }

    public void setNetCustomerId(Integer netCustomerId) {
        this.netCustomerId = netCustomerId;
    }
}
