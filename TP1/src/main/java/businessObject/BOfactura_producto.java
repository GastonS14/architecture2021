package businessObject;

import dao.DAOfactura_producto;
import entity.Factura;
import entity.FacturaProducto;
import factory.Factory;

import java.sql.SQLException;
import java.util.ArrayList;

public class BOfactura_producto {

    private final DAOfactura_producto daoFactProd;

    public BOfactura_producto ( Factory f ) {
        this.daoFactProd = f.getDaoFacProd();
    }

    public void save (FacturaProducto fp ) throws SQLException {
        this.daoFactProd.insert( fp );
    }

    public FacturaProducto findFacturaProductoById(int id) throws SQLException {
        return this.daoFactProd.findFacturaProductoById(id);
    }

    public void saveAll(ArrayList<FacturaProducto> facturaProductos) {
        facturaProductos.forEach(
                facturaProducto -> {
                    try {
                        this.daoFactProd.insert(facturaProducto);
                    } catch (SQLException e) {
                        // It shouldn't do anything here to let the execution continue if some row has an error
                        e.printStackTrace();
                    }
                }
        );
    }
}
