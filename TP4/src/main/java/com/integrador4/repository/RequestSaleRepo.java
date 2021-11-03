package com.integrador4.repository;

import com.integrador4.dto.RequestSaleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RequestSaleRepo extends CrudRepository<RequestSaleEntity, Integer> {

    @Query( value = "SELECT new RequestSaleEntity (vp.sale.id_sale,vp.product.id_product, vp.quantity )" +
            "FROM Sale s JOIN " +
            "SaleProduct vp ON s.id_sale = vp.sale.id_sale JOIN Client c ON c.id_client = s.client.id_client" +
            " WHERE s.date = :date AND c.id_client = :idClient")
    List<RequestSaleEntity> getpurchases(Date date, int idClient );
}
