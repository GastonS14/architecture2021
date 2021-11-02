package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table( name = "product")
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private int id_product;
    @Column
    private String name;
    @Column
    private double price;
    @Column @JsonIgnore
    private int stock;

    public Product() {}

    public Product(Product p ) {
        this.id_product = p.getId_product();
        this.name = p.getName();
        this.price = p.getPrice();
        this.stock = p.getStock();
    }

    public Product(String name, double precio, int stock ){
        this.name = name;
        this.price = precio;
        this.stock = stock;
    }

    public int getId_product() {
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
