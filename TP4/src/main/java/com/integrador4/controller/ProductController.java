package com.integrador4.controller;

import com.integrador4.dto.BestSellProductDto;
import com.integrador4.dto.ProductRequest;
import com.integrador4.entity.Product;
import com.integrador4.extensions.ObjectExtension;
import com.integrador4.repository.BestSellProductRepository;
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
import com.integrador4.service.ProductService;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @Autowired private BestSellProductRepository bestSellProductRepository;

    @GetMapping()
    @Operation( summary = "Get all products", description = "Get list of products",
            responses = @ApiResponse(description = "List of products", responseCode = "200",content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Product.class)))))
    public Iterable<Product> getAll(HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        return this.productService.getAll();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Get product", description = "Get product by id",
    responses = { @ApiResponse(description = "product", responseCode = "200",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))) ,
        @ApiResponse( description = "product not found", responseCode= "404" )})
    public ResponseEntity<Product> getById(@PathVariable Integer id, HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        Optional<Product> product = this.productService.getById(id);
        if ( product.isEmpty() ) return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity( product, HttpStatus.OK);
    }

    @PostMapping
    @Operation( summary = "Add product", description = "Add new product",
            responses = { @ApiResponse(description = "successfully created", responseCode = "201",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))) ,
                    @ApiResponse( description = "Bad request", responseCode= "400" )})
    public ResponseEntity save(@RequestBody ProductRequest body, HttpServletRequest request) {
        logger.info("method={} uri={} body={}",
            request.getMethod(), request.getRequestURI(), ObjectExtension.toJson(body)
        );
        Optional<Product> product = this.productService.save(body);
        if ( product.isEmpty() )
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        return new ResponseEntity( product, HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    @Operation( summary = "Update product", description = "Update an existing product by id",
            responses = { @ApiResponse(description = "product updated", responseCode = "200",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))) ,
                    @ApiResponse( description = "product not found", responseCode= "404" )})
    public ResponseEntity<Product> update(@RequestBody ProductRequest body, @PathVariable Integer id, HttpServletRequest request) {
        logger.info("method={} uri={} body={}",
            request.getMethod(), request.getRequestURI(), ObjectExtension.toJson(body)
        );
        Optional<Product> product = this.productService.update(id, body);
        if ( product.isEmpty() )
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        return new ResponseEntity( product, HttpStatus.OK);
    }

    @GetMapping( "/bestSeller")
    @Operation( summary = "The best sell", description = "Get best seller product",
            responses = { @ApiResponse(description = "the best seller product", responseCode = "200",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BestSellProductDto.class))) ,
                    @ApiResponse( description = "nothing to show", responseCode= "204" )})
    public ResponseEntity<BestSellProductDto> getBestSell ( ) {
        Optional<BestSellProductDto> bestSellProduct = this.bestSellProductRepository.getBestSell();
        if ( bestSellProduct.isEmpty() )
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        return new ResponseEntity( bestSellProduct, HttpStatus.OK);
    }

    @DeleteMapping( "/{id}")
    @Operation( summary = "Delete product",description = "Delete product by id", responses =
            { @ApiResponse(description = "succesfully deleted", responseCode = "200",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))) ,
            @ApiResponse( description = "product not found", responseCode= "404" )})
    public ResponseEntity delete ( @PathVariable Integer id ) {
        Optional<Product> product = this.productService.delete( id );
        if ( product.isEmpty() )
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        return new ResponseEntity( product, HttpStatus.OK );
    }
}
