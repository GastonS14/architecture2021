package businessObject;

import dao.DAOfactura;
import entity.Cliente;
import entity.Factura;
import factory.Factory;

import java.sql.SQLException;
import java.util.ArrayList;

public class BOfactura {

    private final DAOfactura daoFactura;

    public BOfactura (Factory f ) {
        this.daoFactura = f.getDaoFactura();
    }

    public void save ( Factura f ) throws SQLException {
        this.daoFactura.insert( f );
    }

    public Factura findFacturaById ( int id ) throws SQLException {
        return this.daoFactura.findFacturaById( id );
    }

    public void saveAll(ArrayList<Factura> facturas) {
        facturas.forEach(
                factura -> {
                    try {
                        this.daoFactura.insert(factura);
                    } catch (SQLException e) {
                        // It shouldn't do anything here to let the execution continue if some row has an error
                        e.printStackTrace();
                    }
                }
        );
    }

}
