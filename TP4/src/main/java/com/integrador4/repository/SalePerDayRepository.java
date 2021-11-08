package com.integrador4.repository;

import com.integrador4.dto.SalePerDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface SalePerDayRepository extends JpaRepository<SalePerDay, Date> {

    @Query("SELECT new SalePerDay ( s.date, SUM(s.amount) ) FROM Sale s " +
            "WHERE s.date BETWEEN :start AND :end "+
            "GROUP BY s.date ")
    Optional<List<SalePerDay>> findByDate(Date start, Date end);
}
