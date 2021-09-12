package factory;

import dao.*;

/**
 * Define los métodos para la creación de los DAOs.
 */

interface AbstractFactory {

    DAOcliente getDaoCliente();
    DAOproducto getDaoProducto();
    DAOfactura getDaoFactura();
    DAOfactura_producto getDaoFacProd();

}
