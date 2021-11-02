package com.integrador4.repository;

import com.integrador4.dto.RequestVenta;
import com.integrador4.entity.RequestVentaEntidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RequestRepo extends CrudRepository<RequestVentaEntidad, Integer> {

    @Query( value = "SELECT new RequestVentaEntidad (vp.sale.id_sale,vp.product.idProduct, vp.quantity )" +
            "FROM Venta s JOIN " +
            "VentaProducto vp ON s.id_sale = vp.sale.id_sale JOIN Client c ON c.id_client = s.client.id_client" +
            " WHERE s.date = :date AND c.id_client = :idClient")
    List<RequestVentaEntidad> getpurchases(Date date, long idClient );
}
