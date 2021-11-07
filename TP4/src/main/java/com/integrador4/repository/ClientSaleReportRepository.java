package com.integrador4.repository;

import com.integrador4.dto.ClientSaleReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientSaleReportRepository extends CrudRepository<ClientSaleReport, Integer> {

    @Query( value = "select c.id_client, c.name, c.surname, filterSale.amount "+
            "from client c join ( select client_id_client, sum(amount) as amount " +
            "from sale s "+
            "group by client_id_client ) as filterSale ON c.id_client = filterSale.client_id_client", nativeQuery = true)
    List<ClientSaleReport> getClientSaleReport();
}
