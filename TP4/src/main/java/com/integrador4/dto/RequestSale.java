package com.integrador4.dto;

import java.util.ArrayList;

public class RequestSale {

    private int client;
    private ArrayList<SaleProductDto> productQuantity;

    public RequestSale() {
        this.productQuantity = new ArrayList<>();
    }

    public RequestSale(int client, ArrayList<SaleProductDto> rv ) {
        this.client = client;
        this.productQuantity = new ArrayList<>(rv);
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public ArrayList<SaleProductDto> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(ArrayList<SaleProductDto> productQuantity) {
        this.productQuantity = new ArrayList<>(productQuantity);
    }
}
