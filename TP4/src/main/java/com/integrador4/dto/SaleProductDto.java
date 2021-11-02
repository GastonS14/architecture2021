package com.integrador4.dto;

import java.util.ArrayList;

public class SaleProductDto {

    private Integer client;
    private ArrayList<RequestVenta> productQuantity;

    public SaleProductDto() {
        this.productQuantity = new ArrayList<>();
    }

    public SaleProductDto(Integer client, ArrayList<RequestVenta> rv ) {
        this.client = client;
        this.productQuantity = new ArrayList<>(rv);
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public ArrayList<RequestVenta> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(ArrayList<RequestVenta> productQuantity) {
        this.productQuantity = new ArrayList<>(productQuantity);
    }
}
