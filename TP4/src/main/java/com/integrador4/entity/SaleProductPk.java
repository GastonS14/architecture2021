package com.integrador4.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SaleProductPk implements Serializable {

    @Column private int id_sale;
    @Column private int id_product;

    public SaleProductPk() {}

    public SaleProductPk(Product product, Sale sale ){
        this.id_sale = sale.getId_sale();
        this.id_product = product.getId_product();
    }

    public int getId_sale() {
        return id_sale;
    }

    public void setId_sale( int id_sale ) {
        this.id_sale = id_sale;
    }

    public void setId_product( int id_product ) {
        this.id_product = id_product;
    }

    public int getId_product() {
        return id_product;
    }
}
