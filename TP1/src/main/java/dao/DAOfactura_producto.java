package dao;

import entity.FacturaProducto;

import java.sql.SQLException;

public interface DAOfactura_producto {

    void insert ( FacturaProducto fp ) throws SQLException;

    FacturaProducto findFacturaProductoById(int id) throws SQLException;
}
