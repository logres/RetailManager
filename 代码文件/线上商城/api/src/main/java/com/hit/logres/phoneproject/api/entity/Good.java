package com.hit.logres.phoneproject.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Good implements Serializable {
    Integer id;	//商品ID
    String name;	//商品名
    String state;	//商品状态
    BigDecimal priceA;	//价格A
    BigDecimal priceB;	//价格B
    BigDecimal priceC;	//价格C
    BigDecimal purchasePrice;	//进货价
    Integer defaultWarehouseId;	//默认仓库ID
    String info;	//信息
    String barcode;	//条码

    public Good(Integer id, String name, String state, BigDecimal priceA, BigDecimal priceB, BigDecimal priceC, BigDecimal purchasePrice, Integer defaultWarehouseId, String info, String barcode) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.priceA = priceA;
        this.priceB = priceB;
        this.priceC = priceC;
        this.purchasePrice = purchasePrice;
        this.defaultWarehouseId = defaultWarehouseId;
        this.info = info;
        this.barcode = barcode;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getPriceA() {
        return priceA;
    }

    public void setPriceA(BigDecimal priceA) {
        this.priceA = priceA;
    }

    public BigDecimal getPriceB() {
        return priceB;
    }

    public void setPriceB(BigDecimal priceB) {
        this.priceB = priceB;
    }

    public BigDecimal getPriceC() {
        return priceC;
    }

    public void setPriceC(BigDecimal priceC) {
        this.priceC = priceC;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getDefaultWarehouseId() {
        return defaultWarehouseId;
    }

    public void setDefaultWarehouseId(Integer defaultWarehouseId) {
        this.defaultWarehouseId = defaultWarehouseId;
    }
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Good() {
    }
}
