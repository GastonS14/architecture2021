package com.integrador4.service;

import com.integrador4.dto.ProductRequestDto;
import com.integrador4.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(){}

    public Product save(ProductRequestDto requestDto) {
        return this.productRepository.save(requestDto.toProduct());
    }

    public Product update(BigDecimal id, ProductRequestDto requestDto) {
        return this.productRepository.save(requestDto.toProduct(id));
    }

    public Optional<Product> getById (BigDecimal id ) {
        return this.productRepository.findById( id );
    }

    public Iterable<Product> getAll () {
        return this.productRepository.findAll();
    }

}
