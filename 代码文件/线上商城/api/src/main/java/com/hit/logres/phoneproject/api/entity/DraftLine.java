package com.hit.logres.phoneproject.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class DraftLine implements Serializable {
    Integer id;	//草稿项ID
    Integer draftId; //草稿ID
    Integer goodId;	//商品ID
    Integer amount;	//数量
    Integer warehouseId; //仓库ID
    BigDecimal unitPrice; //单价


    public DraftLine(Integer id, Integer draftId, Integer goodId, Integer amount, Integer warehouseId, BigDecimal unitPrice) {
        this.id = id;
        this.draftId = draftId;
        this.goodId = goodId;
        this.amount = amount;
        this.warehouseId = warehouseId;
        this.unitPrice = unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDraftId() {
        return draftId;
    }

    public void setDraftId(Integer draftId) {
        this.draftId = draftId;
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

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public DraftLine() {
    }
}
