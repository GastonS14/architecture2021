package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "product")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private int idProduct;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column
    private int stock;

    @JsonIgnore
    @OneToMany( mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleProduct> sales;

    public Product() {}

    public Product(int idProduct, String name, double price, int stock ){
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.sales = new ArrayList<>();
    }

    public Product(String name, double price, int stock ){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getIdProduct() {
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

    public List<SaleProduct> getSales() {
        if ( this.sales == null )
            this.sales = new ArrayList<>();
        return sales;
    }

    public void setSales(List<SaleProduct> sales) {
        if ( this.sales == null )
            this.sales = new ArrayList<>();
        this.sales = sales;
    }

}
