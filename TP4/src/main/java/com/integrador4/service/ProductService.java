package com.integrador4.service;

import com.integrador4.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ProductRepo;

import java.util.Optional;

@Service
public final class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ProductService(){}

    public Product save (Product p ) {
        return this.productRepo.save( p );
    }

    public Optional<Product> getById (int id ) {
        return this.productRepo.findById( id );
    }

    public Iterable<Product> getAll () {
        return this.productRepo.findAll();
    }

}
