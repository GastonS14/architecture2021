package businessObject;

import dao.DAOcliente;
import dto.DTOcliente;
import entity.Cliente;
import factory.Factory;
import java.sql.SQLException;
import java.util.ArrayList;

public class BOcliente {

    private final DAOcliente daOclienteMySql;

    public BOcliente ( Factory f ) {
        this.daOclienteMySql = f.getDaoCliente();
    }

    public ArrayList<DTOcliente> clientesPorFacturacion () throws SQLException {
        return this.daOclienteMySql.clientesPorFacturacion();
    }

    public void save (Cliente c ) throws SQLException {
        this.daOclienteMySql.insert( c );
    }

    public void saveAll(ArrayList<Cliente> clientes) {
        clientes.forEach(
                cliente -> {
                    try {
                        this.daOclienteMySql.insert(cliente);
                    } catch (SQLException e) {
                        // It shouldn't do anything here to let the execution continue if some row has an error
                        e.printStackTrace();
                    }
                }
        );
    }

    public Cliente findClienteById(int id) throws SQLException {
        return this.daOclienteMySql.findClienteById(id);
    }

}
