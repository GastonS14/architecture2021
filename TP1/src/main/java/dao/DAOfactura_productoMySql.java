package dao;

import entity.FacturaProducto;
import factory.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOfactura_productoMySql implements DAOfactura_producto{

    private final Conexion conexion;

    public DAOfactura_productoMySql(Conexion con ){
        this.conexion = con;
    }

    public void insert ( FacturaProducto fp ) throws SQLException {
        String query = " INSERT INTO Factura_Producto VALUES (?,?,?)";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt( 1,fp.getIdFactura() );
        ps.setInt( 2,fp.getIdProducto() );
        ps.setInt( 3,fp.getCantidad());
        ps.execute();
        this.conexion.commit();
        this.conexion.closeConnection();
    }

    public FacturaProducto findFacturaProductoById (int idFactura ) throws SQLException {
        String query = " SELECT * FROM Factura_Producto WHERE idFactura =  ?";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt(1, idFactura);
        ResultSet rs = ps.executeQuery();
        rs.next();
        FacturaProducto facturaProducto = facturaProductoMapper(rs);
        this.conexion.commit();
        this.conexion.closeConnection();
        return facturaProducto;
    }

    private FacturaProducto facturaProductoMapper(ResultSet rs) {
        try {
            return new FacturaProducto(
                    rs.getInt("idFactura"),
                    rs.getInt("idProducto"),
                    rs.getInt("cantidad")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
