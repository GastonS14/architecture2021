package com.integrador4.service;

import com.integrador4.dto.ProductRequest;
import com.integrador4.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ProductRepository;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(){}

    public Product save(ProductRequest requestDto) {
        return this.productRepository.save(requestDto.toProduct());
    }

    public Product update(Integer id, ProductRequest requestDto) {
        return this.productRepository.save(requestDto.toProduct(id));
    }

    public Optional<Product> getById(Integer id ) {
        return this.productRepository.findById( id );
    }

    public Iterable<Product> getAll() {
        return this.productRepository.findAll();
    }

}
