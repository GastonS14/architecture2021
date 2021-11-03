package com.integrador4.repository;

import com.integrador4.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.Date;
import java.util.List;

public interface SaleRepo extends JpaRepository<Sale, Integer> {

    @Query ( "SELECT v FROM Sale v WHERE v.date = :fecha" )
    public List<Sale> getAllByFecha(Date fecha );

    @Query ( "SELECT v.client.name,v.client.surname,v.date,v.amount FROM Sale v " )
    public List<Sale> reportClienteVentas();
}
