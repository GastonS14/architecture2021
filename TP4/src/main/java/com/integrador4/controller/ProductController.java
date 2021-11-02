package com.integrador4.controller;

import com.integrador4.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.ProductService;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public Iterable<Product> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById (@PathVariable("id") int id ) {
        Optional<Product> product = this.productService.getById( id );
        if ( product.isEmpty() )
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity( product, HttpStatus.OK);
    }

    @PostMapping
    public Product newProduct (@RequestBody Product p ) {
        return   this.productService.save( p );
    }

    @PutMapping
    public Product updateProduct (@RequestBody Product p ) {
        return this.productService.save( p );
    }
}
