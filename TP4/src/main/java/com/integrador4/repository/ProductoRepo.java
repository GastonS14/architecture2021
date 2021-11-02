package com.integrador4.repository;

import com.integrador4.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface ProductoRepo extends CrudRepository<Producto, BigDecimal> {
}
