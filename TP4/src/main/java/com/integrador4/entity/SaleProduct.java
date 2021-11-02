package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

@Entity
@Table( name = "venta_producto")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleProduct {

    @JsonIgnore
    @EmbeddedId
    private VentaProductoPk productSaleID;

    @Column (nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn ( name = "id_product", insertable=false, updatable=false)
    private Product product;

    @JsonIgnore
    @ManyToOne @JoinColumn ( name = "id_sale", insertable=false, updatable=false)
    private Sale sale;

    public SaleProduct(){
    }

    public SaleProduct(int quantity, Product p , Sale v ){
        this.productSaleID = new VentaProductoPk( p, v );
        this.product = p;
        this.sale = v;
        this.quantity = quantity;
    }

    public VentaProductoPk getProductSaleID() {
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
