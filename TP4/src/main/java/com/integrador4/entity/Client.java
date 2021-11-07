package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "client")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int idClient;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @JsonIgnore
    @OneToMany( mappedBy = "client", cascade = {CascadeType.ALL}, orphanRemoval = true )
    private List<Sale> sales;

    public Client(){}

    public Client(String name, String surname ) {
        this.name = name;
        this.surname = surname;
    }

    public Client(int idClient, String name, String surname ) {
        this.idClient = idClient;
        this.name = name;
        this.surname = surname;
        this.sales = this.initSales();
    }

    public int getId() {
        return idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Sale> getSales() {
        return new ArrayList<>(sales);
    }

    public ArrayList<Sale> initSales() {
        if(this.sales == null) return new ArrayList<>();
        return (ArrayList<Sale>) this.getSales();
    }

}
