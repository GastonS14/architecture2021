package com.integrador4.dto;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import java.util.ArrayList;

public class SaleProductDto {

    private int cliente;
    private ArrayList<RequestSaleDto> productosCantidad;

    public SaleProductDto() {
        this.productosCantidad = new ArrayList<>();
    }

    public SaleProductDto(int cliente, List rv ) {
        this.cliente = cliente;
        this.productosCantidad = (ArrayList<RequestSaleDto>) rv;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public ArrayList<RequestSaleDto> getProductosCantidad() {
        return productosCantidad;
    }

    public void setProductosCantidad( ArrayList<RequestSaleDto> productosCantidad) {
        this.productosCantidad = productosCantidad;
    }
}
