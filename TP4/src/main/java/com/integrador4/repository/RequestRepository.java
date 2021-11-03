package com.integrador4.repository;

import com.integrador4.dto.SaleProductDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

public interface RequestRepository extends CrudRepository<SaleProductDto, Integer> {

    @Query(value = "SELECT new SaleProductDto( vp.quantity, vp.product.idProduct )" +
            "FROM Sale s JOIN " +
            "SaleProduct vp ON s.idSale = vp.sale.idSale JOIN Client c ON c.idClient = s.client.idClient" +
            " WHERE s.date = :date AND c.idClient = :idClient")
    List<SaleProductDto> getPurchases(Date date, int idClient );
}
