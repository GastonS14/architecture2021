package com.integrador4.service;

import com.integrador4.dto.ClientRequest;
import com.integrador4.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ClientRepository;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService(){}

    public Optional<Client> save (ClientRequest clientRequest) {
        Client clien = clientRequest.toClient();
        System.out.println(clien);
        Client client = this.clientRepository.save(clien);
        return Optional.of( client );
    }

    public Optional<Client> update (Integer id, ClientRequest clientRequest) {
        if ( this.clientRepository.findById( id ).isEmpty() )
            return Optional.empty();
        return Optional.of( this.clientRepository.save( clientRequest.toClient(id) ) );
    }

    public Iterable<Client> getAll () {
        return this.clientRepository.findAll();
    }

    public Optional<Client> getById (Integer id ) {
        return this.clientRepository.findById( id );
    }

    public Optional<Client> delete( Integer id ) {
        Optional<Client> clientToDelete = this.clientRepository.findById( id );
        if ( clientToDelete.isEmpty() )
            return Optional.empty();
        this.clientRepository.delete( clientToDelete.get());
        return clientToDelete;
    }
}
