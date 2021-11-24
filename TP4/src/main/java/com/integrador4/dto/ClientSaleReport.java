package com.integrador4.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Schema( name = "Sales by client", description = "It has the amount of all purchases for the client")
public class ClientSaleReport {
    @Id private int idClient;
    @Column private String name;
    @Column private String surname;
    @Column private double amount;

    public ClientSaleReport() {

    }

    public ClientSaleReport(int idClient, String name, String surname, double amount) {
        this.idClient = idClient;
        this.name = name;
        this.surname = surname;
        this.amount = amount;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getAmount() {
        return amount;
    }
}
