package com.integrador4.dto;

import java.util.ArrayList;

public class SaleProductDto {

    private Integer cliente;
    private ArrayList<RequestVenta> productosCantidad;

    public SaleProductDto() {
        this.productosCantidad = new ArrayList<>();
    }

    public SaleProductDto(Integer cliente, ArrayList<RequestVenta> rv ) {
        this.cliente = cliente;
        this.productosCantidad = new ArrayList<>(rv);
    }

    public Integer getClient() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public ArrayList<RequestVenta> getProductosCantidad() {
        return productosCantidad;
    }

    public void setProductosCantidad( ArrayList<RequestVenta> productosCantidad) {
        this.productosCantidad = productosCantidad;
    }
}
