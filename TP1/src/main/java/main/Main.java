package main;

import dataBase.EsquemaDB;
import extensions.ResourceLoader;
import helper.Parser;
import businessObject.BOcliente;
import businessObject.BOfactura;
import businessObject.BOfactura_producto;
import businessObject.BOproducto;
import factory.Factory;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        // Solo son aceptados los valores sql
        Factory f = Factory.getInstance( "sql" );

        // Tables creation
        EsquemaDB db = new EsquemaDB( f );
        db.createTables();

        // Servicios - Business Objects
        BOcliente serviceCliente = new BOcliente( f );
        BOfactura serviceFactura = new BOfactura( f );
        BOproducto serviceProd = new BOproducto( f );
        BOfactura_producto servFp = new BOfactura_producto( f );

        // Read CSVs
        String clientesPath = ResourceLoader.getString("clientes.csv");
        String facturasPath = ResourceLoader.getString("facturas.csv");
        String productosPath = ResourceLoader.getString("productos.csv");
        String facturaProductosPath = ResourceLoader.getString("facturas-productos.csv");

        // Load entities
        serviceCliente.saveAll( Parser.readClientes(clientesPath));
        serviceFactura.saveAll( Parser.readFacturas(facturasPath));
        serviceProd.saveAll( Parser.readProductos(productosPath));
        servFp.saveAll( Parser.readFacturasProductos(facturaProductosPath));

        // Resolve problems
        System.out.println( serviceProd.getMasVendido() );
        System.out.println( "\n");
        System.out.println( serviceCliente.clientesPorFacturacion() );

    }
}
