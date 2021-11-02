package com.integrador4.controller;

import com.integrador4.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Product> getById (@PathVariable("id") int id ) {
        return this.productService.getById( id );
    }

    @PostMapping
    public Product newProduct (@RequestBody Product p ) {
        return this.productService.save( p );
    }

    @PutMapping
    public Product updateProduct (@RequestBody Product p ) {
        return this.productService.save( p );
    }
}
