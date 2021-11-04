package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table( name = "sale_product")
public class SaleProduct {

    @JsonIgnore
    @EmbeddedId private SaleProductPk productSaleID;

    @Column ( nullable = false ) private int quantity;

    @ManyToOne
    @JoinColumn ( name = "idProduct", insertable=false, updatable = false)
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn ( name = "idSale", insertable=false, updatable = false)
    private Sale sale;

    public SaleProduct(){
    }

    public SaleProduct(int quantity, Product p , Sale v ){
        this.productSaleID = new SaleProductPk( p, v );
        this.product = p;
        this.sale = v;
        this.quantity = quantity;
    }

    public void setSaleID( int saleID ) {
        this.productSaleID.setIdSale( saleID );
    }

    public SaleProductPk getProductSaleID() {
        return productSaleID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
