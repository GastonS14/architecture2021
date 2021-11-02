package com.integrador4.repository;

import com.integrador4.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query ( "SELECT s FROM Sale s WHERE s.date = :fecha" )
    List<Sale> getAllByFecha(Date fecha );

    @Query ( "SELECT s.client.name, s.client.surname, s.date, s.amount FROM Sale s " )
    List<Sale> reportClienteVentas();
}
