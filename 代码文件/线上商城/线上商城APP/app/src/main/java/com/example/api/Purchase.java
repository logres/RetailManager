package com.example.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Purchase implements Serializable {
    Integer id;	//采购单ID
    Integer goodId;	//商品ID
    Integer warehouseId;	//仓库ID
    BigDecimal price;	//金额
    Integer amount;	//数量
    String supplier;	//供应商
    Timestamp time;	//时间戳

    public Integer getId() {
        return id;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Purchase(Integer id, Integer goodId, Integer warehouseId, BigDecimal price, Integer amount, String supplier, Timestamp time) {
        this.id = id;
        this.goodId = goodId;
        this.warehouseId = warehouseId;
        this.price = price;
        this.amount = amount;
        this.supplier = supplier;
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
