package com.integrador4.controller;

import com.integrador4.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.ClientService;
import java.util.Optional;

@RestController
@RequestMapping( path = "/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public Iterable<Client> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById (@PathVariable("id") int id ) {
        Optional<Client> client = this.clientService.getById( id );
        if ( client.isEmpty() )
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity(client,HttpStatus.OK);
    }

    @PostMapping
    public Client newClient (@RequestBody Client c ) {
        return this.clientService.save( c );
    }

    @PutMapping
    public Client updateClient (@RequestBody Client c ) {
        return this.clientService.save( c );
    }
}
