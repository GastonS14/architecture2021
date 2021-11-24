package com.integrador4.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table ( name = "request_sale_dto")
@Schema( name = "Request sale for each product", description = "It has units & idProduct that client want to buy ")
public class SaleProductDto {
    @Column private int unidades;
    @Id private int idProducto;

    public SaleProductDto(){}

    public SaleProductDto(int unidades, int idProducto ){
        this.unidades = unidades;
        this.idProducto = idProducto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto( int idProducto ) {
        this.idProducto = idProducto;
    }

    public boolean equals ( Object o ) {
        try {
            SaleProductDto rv = (SaleProductDto) o;
            return rv.getIdProducto() == this.idProducto;
        } catch ( Exception e ) {
            return false;
        }
    }
}
