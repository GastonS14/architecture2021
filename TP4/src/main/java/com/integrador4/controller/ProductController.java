package com.integrador4.controller;

import com.integrador4.dto.ProductRequest;
import com.integrador4.entity.Product;
import com.integrador4.extensions.ObjectExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.ProductService;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping()
    public Iterable<Product> getAll(HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getPathInfo());
        return this.productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable BigDecimal id, HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getPathInfo());
        Optional<Product> product = this.productService.getById(id);
        if ( product.isEmpty() ) return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity( product, HttpStatus.OK);
    }

    @PostMapping
    public Product save(@RequestBody ProductRequest body, HttpServletRequest request) {
        logger.info(
            "method={} uri={} body={}",
            request.getMethod(), request.getPathInfo(), ObjectExtension.toJson(body)
        );
        return this.productService.save(body);
    }

    @PutMapping("/{id}")
    public Product update(
        @RequestBody ProductRequest body,
        @PathVariable BigDecimal id,
        HttpServletRequest request
    ) {
        logger.info(
            "method={} uri={} body={}",
            request.getMethod(), request.getPathInfo(), ObjectExtension.toJson(body)
        );
        return this.productService.update(id, body);
    }
}
