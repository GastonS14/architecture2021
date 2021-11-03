package com.integrador4.dto;

public class RequestSaleDto {

    private int unidades;
    private int idProducto;

    public RequestSaleDto(){}

    public RequestSaleDto(int unidades, int idProducto ){
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
            RequestSaleDto rv = (RequestSaleDto) o;
            return rv.getIdProducto() == this.idProducto;
        } catch ( Exception e ) {
            return false;
        }
    }
}
