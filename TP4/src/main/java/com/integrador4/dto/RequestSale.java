package com.integrador4.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;

@Schema( name = "Request sale", description = "It has a client and all the products that he want to buy")
public class RequestSale {

    private int client;
    private final ArrayList<SaleProductDto> productQuantity;

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

    public ArrayList<SaleProductDto> getProductQuantity() {
        return productQuantity;
    }

}
