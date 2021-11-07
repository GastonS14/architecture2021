package com.integrador4.repository;

import com.integrador4.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query ("SELECT s FROM Sale s WHERE s.date = :date")
    List<Sale> findByDate(Date date);

}
