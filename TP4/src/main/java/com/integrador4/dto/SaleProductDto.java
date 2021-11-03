package com.integrador4.dto;

import java.util.ArrayList;

public class SaleProductDto {

    private Integer client;
    private ArrayList<RequestSaleDto> productQuantity;

    public SaleProductDto() {
        this.productQuantity = new ArrayList<>();
    }

    public SaleProductDto(Integer client, ArrayList<RequestSaleDto> rv ) {
        this.client = client;
        this.productQuantity = new ArrayList<>(rv);
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public ArrayList<RequestSaleDto> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(ArrayList<RequestSaleDto> productQuantity) {
        this.productQuantity = new ArrayList<>(productQuantity);
    }
}
