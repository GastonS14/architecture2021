package com.integrador4.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class VentaProductoPk implements Serializable {

    @Column private Long id_sale;
    @Column private BigDecimal id_product;

    public VentaProductoPk () {}

    public VentaProductoPk(Product product, Venta sale ){
        this.id_sale = sale.getId_sale();
        this.id_product = product.getIdProduct();
    }

    public Long getId_sale() {
        return id_sale;
    }

    public void setId_sale(Long id_sale) {
        this.id_sale = id_sale;
    }

    public BigDecimal getId_product() {
        return id_product;
    }

    public void setId_product(BigDecimal id_product) {
        this.id_product = id_product;
    }
}
