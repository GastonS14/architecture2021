package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table( name = "venta_producto")
public class VentaProducto {
    @JsonIgnore
    @EmbeddedId private VentaProductoPk productSaleID;

    @Column ( nullable = false ) private int quantity;

    @ManyToOne @JoinColumn ( name = "id_product", insertable=false, updatable=false)
    private Product product;
    @JsonIgnore
    @ManyToOne @JoinColumn ( name = "id_sale", insertable=false, updatable=false)
    private Venta sale;

    public VentaProducto(){
    }

    public VentaProducto (int quantity, Product p , Venta v ){
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

    public Venta getSale() {
        return sale;
    }

    public void setSale(Venta sale) {
        this.sale = sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
