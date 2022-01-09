package com.example.api;

import java.io.Serializable;

public class Warehouse implements Serializable {
    Integer id;	//仓库ID
    String name;	//仓库名
    String address;//仓库地址，暂不使用

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Warehouse(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
