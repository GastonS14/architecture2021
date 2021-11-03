package com.integrador4.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SaleProductPk implements Serializable {

    @Column
    private int idSale;

    @Column
    private int idProduct;

    public SaleProductPk() {}

    public SaleProductPk(Product product, Sale sale ){
        this.idSale = sale.getIdSale();
        this.idProduct = product.getIdProduct();
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int id_sale ) {
        this.idSale = id_sale;
    }

    public void setIdProduct(int idProduct ) {
        this.idProduct = idProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }
}
