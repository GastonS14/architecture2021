package com.integrador4.repository;

import com.integrador4.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.Date;
import java.util.List;

public interface VentaRepo extends JpaRepository<Venta, Long> {

    @Query ( "SELECT v FROM Venta v WHERE v.date = :fecha" )
    public List<Venta> getAllByFecha( Date fecha );

    @Query ( "SELECT v.client.name,v.client.surname,v.date,v.amount FROM Venta v " )
    public List<Venta> reportClienteVentas();
}
