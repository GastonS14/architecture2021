package dao;

import dto.DTOcliente;
import entity.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOcliente {

    Cliente insert (Cliente c ) throws SQLException;

    ArrayList<DTOcliente> clientesPorFacturacion() throws SQLException;

    Cliente findClienteById(int id) throws SQLException;

}
