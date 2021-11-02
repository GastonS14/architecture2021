package com.integrador4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "client")
public class Cliente {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id_client;
    @Column private String name;
    @Column private String surname;
    @JsonIgnore
    @OneToMany( mappedBy = "id_sale", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Venta> sales;

    public Cliente (){}

    public Cliente ( String name, String surname ) {
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id_client;
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

    public List<Venta> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Venta> sales) {
        this.sales = sales;
    }

}
