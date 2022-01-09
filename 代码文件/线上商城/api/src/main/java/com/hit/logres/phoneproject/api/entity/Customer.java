package com.hit.logres.phoneproject.api.entity;

import java.io.Serializable;

public class Customer implements Serializable {
    Integer id;	//顾客ID
    String name;	//顾客名
    String phone;	//电话
    String address;	//地址
    String type;	//顾客类型
    String info;	//信息

    public Customer(Integer id, String name, String phone, String address, String type, String info) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.info = info;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id){this.id=id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Customer() {
    }
}
