package com.integrador4.controller;

import com.integrador4.dto.BestSellProductDto;
import com.integrador4.dto.ProductRequest;
import com.integrador4.entity.Product;
import com.integrador4.extensions.ObjectExtension;
import com.integrador4.repository.BestSellProductRepository;
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
    public Iterable<Product> getAll(HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        return this.productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id, HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        Optional<Product> product = this.productService.getById(id);
        if ( product.isEmpty() ) return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity( product, HttpStatus.OK);
    }

    @PostMapping
    public Product save(@RequestBody ProductRequest body, HttpServletRequest request) {
        logger.info(
            "method={} uri={} body={}",
            request.getMethod(), request.getRequestURI(), ObjectExtension.toJson(body)
        );
        return this.productService.save(body);
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<BestSellProductDto> getBestSell ( ) {
        Optional<BestSellProductDto> bestSellProduct = this.bestSellProductRepository.getBestSell();
        if ( bestSellProduct.isEmpty() )
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        return new ResponseEntity( bestSellProduct, HttpStatus.OK);
    }

    @DeleteMapping( "/{id}")
    public ResponseEntity delete ( @PathVariable Integer id ) {
        Optional<Product> product = this.productService.delete( id );
        if ( product.isEmpty() )
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        return new ResponseEntity( product, HttpStatus.OK );
    }
}
