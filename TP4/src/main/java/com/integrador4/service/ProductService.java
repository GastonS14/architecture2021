package com.integrador4.service;

import com.integrador4.dto.ProductRequest;
import com.integrador4.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ProductRepository;
import java.util.Optional;

@Service
public final class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(){}

    public Optional<Product> save( ProductRequest requestDto ) {
        Optional<Product> product = Optional.of( this.productRepository.save( requestDto.toProduct() ) );
        return product;
    }

    public Optional<Product> update(Integer id, ProductRequest requestDto) {
        if( this.productRepository.findById( id ).isEmpty() )
            return Optional.empty();
        return Optional.of( this.productRepository.save(requestDto.toProduct(id)) );
    }

    public Optional<Product> getById(Integer id ) {
        return this.productRepository.findById( id );
    }

    public Iterable<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Optional<Product> delete ( Integer id ) {
        Optional<Product> productToDelete = this.productRepository.findById( id );
        if ( productToDelete.isEmpty() )
            return Optional.empty();
        this.productRepository.delete( productToDelete.get() );
        return productToDelete;
    }
}
