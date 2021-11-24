package com.integrador4.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Schema( name = "Sales per day", description = "show sales in order by dates ")
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
