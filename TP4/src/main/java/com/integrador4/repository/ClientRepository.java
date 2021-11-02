package com.integrador4.repository;

import com.integrador4.dto.RequestVenta;
import com.integrador4.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Query( value = "SELECT vp.id_product, vp.quantity FROM sale s JOIN " +
            "venta_producto vp ON s.id_sale = vp.id_sale JOIN client c ON c.id_client = s.client_id_client" +
            " WHERE s.date = :date AND c.id_client = :idClient",
            nativeQuery = true)
    List<RequestVenta> getpurchases(Date date, long idClient );

}
