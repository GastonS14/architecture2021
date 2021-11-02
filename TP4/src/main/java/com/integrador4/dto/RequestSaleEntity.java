package com.integrador4.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RequestSaleEntity {
    @Id private int id_sale;
    @Column private int quantity;
    @Column private int id_product;

    public RequestSaleEntity(){}

    public RequestSaleEntity(int id, int idProducto, int unidades){
        this.id_sale = id;
        this.quantity = unidades;
        this.id_product = idProducto;
    }

    public long getId() {
        return id_sale;
    }

    public void setId( int id ) {
        this.id_sale = id;
    }

    public int getUnidades() {
        return quantity;
    }

    public void setUnidades(int unidades) {
        this.quantity = unidades;
    }

    public int getIdProducto() {
        return id_product;
    }

    public void setIdProducto( int idProducto) {
        this.id_product = idProducto;
    }

    public boolean equals ( Object o ) {
        try {
            RequestSaleDto rv = (RequestSaleDto) o;
            return rv.getIdProducto() == this.id_product;
        } catch ( Exception e ) {
            return false;
        }
    }
}
