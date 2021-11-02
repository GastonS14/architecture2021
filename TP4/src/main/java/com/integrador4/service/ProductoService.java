package com.integrador4.service;

import com.integrador4.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ProductoRepo;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepo productoRepo;

    public ProductoService (){}

    public Producto save (Producto p ) {
        this.productoRepo.save( p );
        return p;
    }

    public Optional<Producto> getById (BigDecimal id ) {
        return this.productoRepo.findById( id );
    }

    public Iterable<Producto> getAll () {
        return this.productoRepo.findAll();
    }

}
