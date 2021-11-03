package com.integrador4.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RequestSaleEntity {

    @Id
    private int idSale;

    @Column
    private int quantity;

    @Column
    private int idProduct;

    public RequestSaleEntity(){}

    public RequestSaleEntity(int id, int idProducto, int unidades){
        this.idSale = id;
        this.quantity = unidades;
        this.idProduct = idProducto;
    }

    public long getId() {
        return idSale;
    }

    public void setId( int id ) {
        this.idSale = id;
    }

    public int getUnidades() {
        return quantity;
    }

    public void setUnidades(int unidades) {
        this.quantity = unidades;
    }

    public int getIdProducto() {
        return idProduct;
    }

    public void setIdProducto( int idProducto) {
        this.idProduct = idProducto;
    }

    public boolean equals ( Object o ) {
        try {
            RequestSaleDto rv = (RequestSaleDto) o;
            return rv.getIdProducto() == this.idProduct;
        } catch ( Exception e ) {
            return false;
        }
    }
}
