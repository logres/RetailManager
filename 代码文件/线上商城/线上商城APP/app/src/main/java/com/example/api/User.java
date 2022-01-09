package com.example.api;

import java.io.Serializable;

public class User implements Serializable {
    Integer id;	//员工ID
    String authority;	//员工等级
    String password;	//密码
    String username;	//用户名
    String name; //名字
    String phoneNumber; //电话号码
    String gender; //性别

    public User(Integer id, String authority, String password, String username, String name, String phoneNumber, String gender) {
        this.id = id;
        this.authority = authority;
        this.password = password;
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
