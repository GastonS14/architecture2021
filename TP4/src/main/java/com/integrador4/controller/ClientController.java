package com.integrador4.controller;

import com.integrador4.dto.ClientRequest;
import com.integrador4.entity.Client;
import com.integrador4.extensions.ObjectExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.ClientService;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping
    public Iterable<Client> getAll(HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById (@PathVariable Integer id, HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        Optional<Client> client = this.clientService.getById( id );
        if ( client.isEmpty() )
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity(client, HttpStatus.OK);
    }

    @PostMapping
    public Client save(@RequestBody ClientRequest body, HttpServletRequest request) {
        logger.info(
            "method={} uri={} body={}",
            request.getMethod(), request.getRequestURI(), ObjectExtension.toJson(body)
        );
        return this.clientService.save(body);
    }

    /**
     * @param id of a client
     * @param body to update a client
     * @return a Client entity
     */
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody ClientRequest body, HttpServletRequest request) {
        logger.info("method={} uri={} body={}",
            request.getMethod(), request.getRequestURI(), ObjectExtension.toJson(body)
        );
        Optional<Client> client = this.clientService.update(id, body);
        if ( client.isEmpty() )
            return new ResponseEntity( HttpStatus.NOT_FOUND);
        return new ResponseEntity<>( client, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete ( @PathVariable Integer id ) {
        Optional<Client> client = this.clientService.delete( id );
        if ( client.isEmpty() )
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        return new ResponseEntity( client, HttpStatus.OK );
    }
}
