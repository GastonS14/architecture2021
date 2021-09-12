package dao;

import entity.Producto;
import java.sql.SQLException;


public interface DAOproducto {

    void insert(Producto p) throws SQLException;

    Producto getMasVendido () throws SQLException;

    Producto getProductoById ( int idProducto ) throws SQLException;

}
