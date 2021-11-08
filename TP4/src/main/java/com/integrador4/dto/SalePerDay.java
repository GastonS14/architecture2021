package com.integrador4.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SalePerDay {
    @Id @Temporal(TemporalType.DATE) private Date date;
    @Column private double amount;

    public SalePerDay(){}

    public SalePerDay ( Date date, double amount ) {
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

}
