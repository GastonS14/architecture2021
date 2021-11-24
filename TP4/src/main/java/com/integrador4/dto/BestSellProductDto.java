package com.integrador4.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Schema( name = "The Best seller product", description = "A report for the best seller product in the history")
public class BestSellProductDto {
    @Id
    private int idProduct;
    @Column private String name;
    @Column private double price;
    @Column private int stock;
    @Column
    private int amount_of_sells;

    public BestSellProductDto () {
    }

    public BestSellProductDto ( int id, String name, double price, int stock, int amount ) {
        this.idProduct = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.amount_of_sells = amount;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public int getAmount_of_sells() {
        return amount_of_sells;
    }

    public void setAmount_of_sells(int amount_of_sells) {
        this.amount_of_sells = amount_of_sells;
    }
}
