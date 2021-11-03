package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "sale")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sale {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer idSale;

    @Column
    private Date date;

    @ManyToOne @JoinColumn( referencedColumnName = "id_client")
    private Client client;

    @Column
    private Double amount;

    @OneToMany ( mappedBy = "sale", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<SaleProduct> productsSold;

    public Sale() {
        this.productsSold = new ArrayList<>();
    }

    public Sale(Client client, Date date) {
        this.date = date;
        this.client = client;
    }

    public Sale(Integer id, Client client, Date date) {
        this.idSale = id;
        this.date = date;
        this.client = client;
    }

    public Sale(Sale v ) {
        this.amount = v.getAmount();
        this.date = v.getDate();
        this.client = v.getClient();
        this.idSale = v.getIdSale();
        //this.productsSold = new ArrayList<>();
        this.productsSold = v.getProductsSold();
    }

    public Integer getIdSale() {
        return idSale;
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

    public List<SaleProduct> getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(ArrayList<SaleProduct> productsSold) {
        this.productsSold = productsSold;
    }

    public void addProduct ( SaleProduct vp ) {
        if ( this.productsSold.contains( vp ) ) {
            SaleProduct repeated = this.productsSold.get( this.productsSold.indexOf( vp ) );
            vp.setQuantity( vp.getQuantity() + repeated.getQuantity() );
        }
        this.productsSold.add( vp );
    }
}
