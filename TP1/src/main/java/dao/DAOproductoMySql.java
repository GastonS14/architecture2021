package dao;

import dto.DTOproducto;
import entity.Producto;
import factory.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOproductoMySql implements DAOproducto{

    private final Conexion conexion;

    public DAOproductoMySql(Conexion con ) {
        this.conexion = con;
    }

    public void insert ( Producto p ) throws SQLException {
        String query = " INSERT INTO Producto VALUES (?,?,?)";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt( 1,p.getIdProducto() );
        ps.setString(2, p.getNombre());
        ps.setInt( 3,p.getValor());
        ps.execute();
        this.conexion.commit();
        this.conexion.closeConnection();
    }

    public Producto getMasVendido ( ) throws SQLException{
        String query = """
                        select p.idProducto AS idProducto, SUM(cantidad) * p.valor AS cantidad
                        FROM Producto p JOIN Factura_Producto FP on p.idProducto = FP.idProducto
                        GROUP BY p.idProducto
                        ORDER BY cantidad DESC
                        LIMIT 1 """.trim();
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ResultSet rs = ps.executeQuery();
        rs.next();
        this.conexion.commit();
        DTOproducto resultado = new DTOproducto( rs.getInt("idProducto"), rs.getInt("cantidad"));
        Producto p = getProductoById( resultado.getIdProducto() );
        this.conexion.closeConnection();  //despues del commit connection closed xD
        return p;
    }

    public Producto getProductoById ( int idProducto ) throws SQLException {
        String query = "SELECT * FROM Producto WHERE idProducto = ?";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt(1, idProducto);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Producto producto = productoMapper(rs);
        this.conexion.commit();
        this.conexion.closeConnection();
        return producto;
    }

    private Producto productoMapper(ResultSet rs) {
        try {
            return new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("nombre"),
                    rs.getInt("valor")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
