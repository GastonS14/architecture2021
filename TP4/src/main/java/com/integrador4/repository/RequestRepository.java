package com.integrador4.repository;

import com.integrador4.dto.RequestSaleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

public interface RequestRepository extends CrudRepository<RequestSaleEntity, Integer> {

    @Query(value = "SELECT new RequestSaleEntity (vp.sale.idSale,vp.product.idProduct, vp.quantity )" +
            "FROM Sale s JOIN " +
            "SaleProduct vp ON s.idSale = vp.sale.idSale JOIN Client c ON c.idClient = s.client.idClient" +
            " WHERE s.date = :date AND c.idClient = :idClient")
    List<RequestSaleEntity> getPurchases(Date date, long idClient );
}
