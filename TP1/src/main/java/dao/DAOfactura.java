package dao;

import entity.Factura;

import java.sql.SQLException;

public interface DAOfactura {

    void insert ( Factura f ) throws SQLException;

    Factura findFacturaById( int id ) throws SQLException ;
}
