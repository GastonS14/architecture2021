package com.integrador4.entity;

import com.integrador4.dto.RequestVenta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class RequestVentaEntidad {
    @Id private long id_sale;
    @Column private int quantity;
    @Column private BigDecimal id_product;

    public RequestVentaEntidad(){}

    public RequestVentaEntidad ( long id, BigDecimal idProducto, int unidades){
        this.id_sale = id;
        this.quantity = unidades;
        this.id_product = idProducto;
    }

    public long getId() {
        return id_sale;
    }

    public void setId(long id) {
        this.id_sale = id;
    }

    public int getUnidades() {
        return quantity;
    }

    public void setUnidades(int unidades) {
        this.quantity = unidades;
    }

    public BigDecimal getIdProducto() {
        return id_product;
    }

    public void setIdProducto(BigDecimal idProducto) {
        this.id_product = idProducto;
    }

    public boolean equals ( Object o ) {
        try {
            RequestVenta rv = (RequestVenta) o;
            return rv.getIdProducto() == this.id_product;
        } catch ( Exception e ) {
            return false;
        }
    }
}
