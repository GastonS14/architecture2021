package com.integrador4.repository;

import com.integrador4.dto.RequestVenta;
import com.integrador4.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface ClienteRepo extends CrudRepository<Cliente, Long> {

    @Query( value = "SELECT vp.id_product, vp.quantity FROM sale s JOIN " +
            "venta_producto vp ON s.id_sale = vp.id_sale JOIN client c ON c.id_client = s.client_id_client" +
            " WHERE s.date = :date AND c.id_client = :idClient",
            nativeQuery = true)
    List<RequestVenta> getpurchases(Date date, long idClient );

}