package com.integrador4.service;

import com.integrador4.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ClienteRepo;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepo clienteRepo;

    public ClienteService(){}

    public Cliente save (Cliente c ) {
        return this.clienteRepo.save( c );
    }

    public Iterable<Cliente> getAll () {
        return this.clienteRepo.findAll();
    }

    public Optional<Cliente> getById (long id ) {
        return this.clienteRepo.findById( id );
    }

}
