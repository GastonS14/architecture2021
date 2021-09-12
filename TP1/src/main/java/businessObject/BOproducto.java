package businessObject;

import dao.DAOproducto;
import entity.Factura;
import entity.Producto;
import factory.Factory;

import java.sql.SQLException;
import java.util.ArrayList;

public class BOproducto {

    private final DAOproducto daOproductoMySql;

    public BOproducto ( Factory f ) {
        this.daOproductoMySql = f.getDaoProducto();
    }

    public void save ( Producto p ) throws SQLException {
        this.daOproductoMySql.insert( p );
    }

    public Producto getMasVendido ( ) throws SQLException {
        return this.daOproductoMySql.getMasVendido();
    }

    public void saveAll(ArrayList<Producto> productos) {
        productos.forEach(
                producto -> {
                    try {
                        this.daOproductoMySql.insert(producto);
                    } catch (SQLException e) {
                        // It shouldn't do anything here to let the execution continue if some row has an error
                        e.printStackTrace();
                    }
                }
        );
    }

}
