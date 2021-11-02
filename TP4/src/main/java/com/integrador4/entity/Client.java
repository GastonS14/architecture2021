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
    private Integer idClient;

    @Column
    private String name;

    @Column
    private String surname;

    @JsonIgnore
    @OneToMany( mappedBy = "idSale", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Sale> sales;

    public Client(){}

    public Client(String name, String surname ) {
        this.name = name;
        this.surname = surname;
    }

    public Client(Integer idClient, String name, String surname ) {
        this.idClient = idClient;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
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
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = new ArrayList<>(sales);
    }

}
