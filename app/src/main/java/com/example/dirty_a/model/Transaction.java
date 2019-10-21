package com.example.dirty_a.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private Date date;
    private String description;
    private String owner;
    private BigDecimal amount;

    public Transaction(Date date, String description, String owner, BigDecimal amount) {
        this.date = date;
        this.description = description;
        this.owner = owner;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
