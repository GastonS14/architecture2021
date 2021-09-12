package dao;

import dto.DTOcliente;
import entity.Cliente;
import factory.Conexion;
import java.sql.*;
import java.util.ArrayList;

public class DAOclienteMySql implements DAOcliente {

    private final Conexion conexion;

    public DAOclienteMySql(Conexion conexion ) {
        this.conexion = conexion;
    }

    public Cliente insert (Cliente c ) throws SQLException {
        String query = " INSERT INTO Cliente VALUES (?,?,?)";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt(1, c.getIdCliente() );
        ps.setString( 2, c.getNombre() );
        ps.setString( 3, c.getEmail() );
        ps.execute();
        this.conexion.commit();
        this.conexion.closeConnection();
        return null;

    }

    public Cliente findClienteById ( int id ) throws SQLException {
        String query = " SELECT * FROM Cliente WHERE idCliente = ?";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt(1, id );
        ResultSet rs = ps.executeQuery();
        rs.next();
        Cliente cliente = clienteMapper(rs);
        this.conexion.commit();
        this.conexion.closeConnection();
        return cliente;
    }

    public ArrayList<DTOcliente> clientesPorFacturacion( ) throws SQLException {
        ArrayList<DTOcliente> resultado = new ArrayList<>();
        String query = """
                select c.idCliente, c.nombre, c.email, result.valoresTotales
                from Cliente c JOIN
                                    ( select idCliente, SUM(valoresPorFacturas) as valoresTotales
                                       from ( select idFactura, SUM(valorPorFactura) as valoresPorFacturas
                                               from (
                                                select idFactura,fp.idProducto, cantidad * p.valor as valorPorFactura
                                                from Factura_Producto fp join Producto p on fp.idProducto = p.idProducto
                                                group by fp.idProducto, idFactura ) AS tableOne
                                               group by idFactura ) AS tableTwo JOIN Factura f ON f.idFactura = tableTwo.idFactura
                                    group by idCliente
                                    order by valoresTotales DESC ) AS result ON c.idCliente = result.idCliente""".trim();
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ResultSet rs = ps.executeQuery();
        while ( rs.next() ) {
            resultado.add( dtoClienteMapper(rs));
        }
        this.conexion.commit();
        this.conexion.closeConnection();
        return resultado;
    }

    private DTOcliente dtoClienteMapper(ResultSet rs) {
        try {
            return new DTOcliente(
                    rs.getInt("idCliente"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getInt( "valoresTotales")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Cliente clienteMapper(ResultSet rs) {
        try {
            return new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
