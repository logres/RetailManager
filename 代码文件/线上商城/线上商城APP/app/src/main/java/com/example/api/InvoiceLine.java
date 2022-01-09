package com.example.api;

import java.io.Serializable;
import java.math.BigDecimal;

public class InvoiceLine implements Serializable {
    Integer id;	//订单项ID
    Integer invoiceId;	//订单ID
    Integer goodId;	//商品ID
    Integer warehouseId;	//仓库ID
    BigDecimal unitPrice;	//单价
    Integer amount;	//数量


    public InvoiceLine(Integer id, Integer invoiceId, Integer goodId, Integer warehouseId, BigDecimal unitPrice, Integer amount) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.goodId = goodId;
        this.warehouseId = warehouseId;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
