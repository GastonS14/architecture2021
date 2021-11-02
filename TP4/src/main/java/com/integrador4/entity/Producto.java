package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table( name = "product")
public class Producto {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private BigDecimal id_product;
    @Column
    private String name;
    @Column
    private double price;
    @Column @JsonIgnore
    private int stock;

    public Producto () {}

    public Producto ( Producto p ) {
        this.id_product = p.getId_product();
        this.name = p.getName();
        this.price = p.getPrice();
        this.stock = p.getStock();
    }

    public Producto ( String name, double precio, int stock ){
        this.name = name;
        this.price = precio;
        this.stock = stock;
    }

    public BigDecimal getId_product() {
        return id_product;
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
