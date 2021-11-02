package com.integrador4.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RequestVenta {

    private int unidades;
    private BigDecimal idProducto;

    public RequestVenta(){}

    public RequestVenta ( int unidades, BigDecimal idProducto ){
        this.unidades = unidades;
        this.idProducto = idProducto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public BigDecimal getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(BigDecimal idProducto) {
        this.idProducto = idProducto;
    }

    public boolean equals ( Object o ) {
        try {
            RequestVenta rv = (RequestVenta) o;
            return rv.getIdProducto() == this.idProducto;
        } catch ( Exception e ) {
            return false;
        }
    }
}
