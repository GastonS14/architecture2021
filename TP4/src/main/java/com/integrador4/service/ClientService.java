package com.integrador4.service;

import com.integrador4.dto.ClientRequestDto;
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

    public Client save (ClientRequestDto clientRequestDto ) {
        return this.clientRepository.save(clientRequestDto.toClient());
    }

    public Client update (Integer id, ClientRequestDto clientRequestDto) {
        return this.clientRepository.save(clientRequestDto.toClient(id));
    }

    public Iterable<Client> getAll () {
        return this.clientRepository.findAll();
    }

    public Optional<Client> getById (Integer id ) {
        return this.clientRepository.findById( id );
    }

}
