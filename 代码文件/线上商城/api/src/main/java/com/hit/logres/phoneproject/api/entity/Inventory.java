package com.hit.logres.phoneproject.api.entity;

import java.io.Serializable;

public class Inventory implements Serializable {
    Integer id;	//库存项ID
    Integer warehouseId;	//仓库ID
    Integer goodId;	//商品ID
    Integer amount;	//数量
    String info;	//信息

    public Inventory(Integer id, Integer warehouseId, Integer goodId, Integer amount, String info) {
        this.id = id;
        this.warehouseId = warehouseId;
        this.goodId = goodId;
        this.amount = amount;
        this.info = info;
    }


    public Integer getId() {
        return id;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Inventory() {
    }
}
