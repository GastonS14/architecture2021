package com.integrador4.service;

import com.integrador4.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ClientRepo;

import java.util.Optional;

@Service
public final class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public ClientService(){}

    public Client save (Client c ) {
        return this.clientRepo.save( c );
    }

    public Iterable<Client> getAll () {
        return this.clientRepo.findAll();
    }

    public Optional<Client> getById (int id ) {
        return this.clientRepo.findById( id );
    }

}
