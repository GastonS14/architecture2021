package com.integrador4.repository;

import com.integrador4.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
