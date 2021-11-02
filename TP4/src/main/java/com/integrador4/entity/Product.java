package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table( name = "product")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private BigDecimal idProduct;
    @Column
    private String name;
    @Column
    private Double price;
    @Column @JsonIgnore
    private Integer stock;

    public Product() {}

    public Product(BigDecimal id, String name, double price, int stock ){
        this.idProduct = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, double price, int stock ){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public BigDecimal getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
