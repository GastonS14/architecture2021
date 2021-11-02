package com.integrador4.repository;

import com.integrador4.entity.VentaProducto;
import com.integrador4.entity.VentaProductoPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaProductoRepo extends JpaRepository<VentaProducto, VentaProductoPk> {
/*
    @Query ( nativeQuery = true,
            value = " SELECT SUM(unidades) AS unidades, v.idProducto FROM  " +
                        "SELECT v, vp.unidades FROM Venta v " +
                            "JOIN VentaProducto vp ON v.id = vp.id_venta ) AS totalVentas" +
                    "WHERE v.idProducto = totalVentas.idProducto")
    public Producto getMasVendido();

 */
}
