package com.integrador4.repository;

import com.integrador4.dto.BestSellProductDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface BestSellProductRepository extends CrudRepository<BestSellProductDto, Integer> {

    @Query(value =
            "SELECT p.id_product, p.name, p.price, p.stock, result.amount_of_sells " +
            "FROM product p JOIN" +
                " ( SELECT id_product, SUM( quantity ) AS amount_of_sells" +
                " FROM sale_product sp" +
                " GROUP BY id_product" +
                " ORDER BY amount_of_sells DESC limit 1 ) AS result " +
            "ON p.id_product = result.id_product ", nativeQuery = true)
    Optional<BestSellProductDto> getBestSell();
}
