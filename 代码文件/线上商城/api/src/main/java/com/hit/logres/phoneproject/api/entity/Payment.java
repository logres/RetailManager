package com.hit.logres.phoneproject.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment implements Serializable {
    Integer id;	//付款项ID
    Integer invoiceId;	//订单ID
    BigDecimal amount;	//金额
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    Timestamp time;	//时间戳

    public Integer getId() {
        return id;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Payment(Integer id, Integer invoiceId, BigDecimal amount, Timestamp time) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Payment() {
    }
}
