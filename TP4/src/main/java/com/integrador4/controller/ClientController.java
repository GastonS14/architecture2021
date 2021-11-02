package com.integrador4.controller;

import com.integrador4.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.ClienteService;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping( path = "/clients")
public class ClientController {
    @Autowired
    private ClienteService clientService;

    @GetMapping
    public Iterable<Cliente> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> getById (@PathVariable("id") long id ) {
        return this.clientService.getById( id );
    }

    @PostMapping
    public Cliente newClient (@RequestBody Cliente c ) {
        return this.clientService.save( c );
    }

    @PutMapping
    public Cliente updateClient ( @RequestBody Cliente c ) {
        return this.clientService.save( c );
    }
}
