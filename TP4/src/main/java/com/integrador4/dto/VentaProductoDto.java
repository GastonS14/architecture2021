package com.integrador4.dto;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import java.util.ArrayList;

public class VentaProductoDto {

    private Integer cliente;
    private ArrayList<RequestVenta> productosCantidad;

    public VentaProductoDto () {
        this.productosCantidad = new ArrayList<>();
    }

    public VentaProductoDto ( Integer cliente, List rv ) {
        this.cliente = cliente;
        this.productosCantidad = (ArrayList<RequestVenta>) rv;
    }

    public Integer getCliente() {
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
