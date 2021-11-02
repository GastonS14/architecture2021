package com.integrador4.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "sale")
public class Venta {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id_sale;
    @Column
    private Date date;
    @ManyToOne @JoinColumn( referencedColumnName = "id_client")
    private Client client;
    @Column
    private double amount;
    @OneToMany ( mappedBy = "sale", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<VentaProducto> productsSold;

    public Venta() {
        this.productsSold = new ArrayList<>();
    }

    public Venta ( Venta v ) {
        this.amount = v.getAmount();
        this.date = v.getDate();
        this.client = v.getClient();
        this.id_sale = v.getId_sale();
        //this.productsSold = new ArrayList<>();
        this.productsSold = v.getProductsSold();
    }

    public long getId_sale() {
        return id_sale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient( Client client) {
        this.client = client;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<VentaProducto> getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(ArrayList<VentaProducto> productsSold) {
        this.productsSold = productsSold;
    }

    public boolean addProduct ( VentaProducto vp ) {
        if ( this.productsSold.contains( vp ) ) {
            VentaProducto repeated = this.productsSold.get( this.productsSold.indexOf( vp ) );
            vp.setQuantity( vp.getQuantity() + repeated.getQuantity() );
        }
        return this.productsSold.add( vp );
    }
}
