package dao;

import entity.Factura;
import factory.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOfacturaMySql implements DAOfactura {

    private final Conexion conexion;

    public DAOfacturaMySql(Conexion con ){
        this.conexion = con;
    }

    public void insert ( Factura f ) throws SQLException {
        String query = " INSERT INTO Factura VALUES (?,?)";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt( 1,f.getIdFactura() );
        ps.setInt( 2,f.getIdCliente() );
        ps.execute();
        this.conexion.commit();
        this.conexion.closeConnection();
    }

    public Factura findFacturaById ( int idFactura ) throws SQLException {
        String query = " SELECT * FROM Factura WHERE idFactura =  ?";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt(1, idFactura);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Factura factura = facturaMapper(rs);
        this.conexion.commit();
        this.conexion.closeConnection();
        return factura;
    }

    private Factura facturaMapper(ResultSet rs) {
        try {
            return new Factura(
                    rs.getInt("idFactura"),
                    rs.getInt("idCliente")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
