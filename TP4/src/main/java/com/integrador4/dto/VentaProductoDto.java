package com.integrador4.dto;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import java.util.ArrayList;

public class VentaProductoDto {

    private long cliente;
    private ArrayList<RequestVenta> productosCantidad;

    public VentaProductoDto () {
        this.productosCantidad = new ArrayList<>();
    }

    public VentaProductoDto ( long cliente, List rv ) {
        this.cliente = cliente;
        this.productosCantidad = (ArrayList<RequestVenta>) rv;
    }

    public long getCliente() {
        return cliente;
    }

    public void setCliente(long cliente) {
        this.cliente = cliente;
    }

    public ArrayList<RequestVenta> getProductosCantidad() {
        return productosCantidad;
    }

    public void setProductosCantidad( ArrayList<RequestVenta> productosCantidad) {
        this.productosCantidad = productosCantidad;
    }
}
