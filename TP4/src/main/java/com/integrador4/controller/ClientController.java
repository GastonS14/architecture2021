package com.integrador4.controller;
import com.integrador4.dto.ClientRequest;
import com.integrador4.entity.Client;
import com.integrador4.extensions.ObjectExtension;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation( summary = "Get all clients",description = "Get list of clients", responses =
    @ApiResponse(description = "List of clients", responseCode = "200",content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Client.class)))))
    public Iterable<Client> getAll(HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Get client",description = "Get client by id", responses = {
            @ApiResponse(description = "Client", responseCode = "200",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class))) ,
            @ApiResponse( description = "client not found", responseCode= "404" )})
    public ResponseEntity<Client> getById (@PathVariable Integer id, HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        Optional<Client> client = this.clientService.getById( id );
        if ( client.isEmpty() )
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity(client, HttpStatus.OK);
    }

    @PostMapping
    @Operation( summary = "Add client",description = "Add new client", responses = {
            @ApiResponse(description = "successfully created", responseCode = "200",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class))) ,
            @ApiResponse( description = "bad request", responseCode= "400" )})
    public ResponseEntity save(@RequestBody ClientRequest body, HttpServletRequest request) {
        logger.info("method={} uri={} body={}",
            request.getMethod(), request.getRequestURI(), ObjectExtension.toJson(body)
        );
        Optional<Client> newClient = this.clientService.save(body);
        if ( newClient.isEmpty() )
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
        return new ResponseEntity( newClient, HttpStatus.CREATED );
    }

    /**
     * @param id of a client
     * @param body to update a client
     * @return a Client entity
     */
    @PutMapping("/{id}")
    @Operation( summary = "Update an existing client", responses = {
            @ApiResponse(description = "successfully updated", responseCode = "200",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class))) ,
            @ApiResponse( description = "client not found", responseCode= "404" )})
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
    @Operation( summary = "Delete an existing client", responses = {
            @ApiResponse(description = "successfully deleted", responseCode = "200",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class))) ,
            @ApiResponse( description = "client not found", responseCode= "404" )})
    public ResponseEntity delete ( @PathVariable Integer id ) {
        Optional<Client> client = this.clientService.delete( id );
        if ( client.isEmpty() )
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        return new ResponseEntity( client, HttpStatus.OK );
    }
}
