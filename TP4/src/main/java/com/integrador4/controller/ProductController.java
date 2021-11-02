package com.integrador4.controller;

import com.integrador4.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.ProductoService;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductoService productService;

    @GetMapping()
    public Iterable<Producto> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Producto> getById ( @PathVariable("id") BigDecimal id ) {
        return this.productService.getById( id );
    }

    @PostMapping
    public Producto newProduct (@RequestBody Producto p ) {
        return this.productService.save( p );
    }

    @PutMapping
    public Producto updateProduct ( @RequestBody Producto p ) {
        return this.productService.save( p );
    }
}
