package helper;

import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class Parser {

    public static ArrayList<Producto> readProductos(String path ) throws IOException {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            int idProducto = Integer.parseInt(row.get("idProducto"));
            int valor = Integer.parseInt(row.get("valor"));
            String aux = row.get("nombre");
            Producto p = new Producto(idProducto, aux, valor);
            listaProductos.add(p);
        }
        return listaProductos;
    }

    public static ArrayList<Cliente> readClientes(String path ) throws IOException {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            int idCliente = Integer.parseInt(row.get("idCliente"));
            Cliente c = new Cliente(idCliente, row.get("nombre"), row.get("email"));
            listaClientes.add( c );
        }
        return listaClientes;
    }

    public static ArrayList<Factura> readFacturas(String path ) throws IOException {
        ArrayList<Factura> listaFacturas = new ArrayList<>();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader( path ) );
        for( CSVRecord row:parser ) {
            int idFactura = Integer.parseInt( row.get("idFactura") );
            int idCliente = Integer.parseInt( row.get("idCliente") );
            Factura p = new Factura( idFactura, idCliente );
            listaFacturas.add( p );
        }
        return listaFacturas;
    }

    public static ArrayList<FacturaProducto> readFacturasProductos(String path ) throws IOException {
        ArrayList<FacturaProducto> listaFacturaProductos = new ArrayList<>();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader( path ) );
        for( CSVRecord row:parser ) {
            int idFactura = Integer.parseInt( row.get("idFactura") );
            int idProducto = Integer.parseInt( row.get("idProducto") );
            int cantidad = Integer.parseInt( row.get("cantidad") );
            FacturaProducto fp = new FacturaProducto( idFactura, idProducto, cantidad );
            listaFacturaProductos.add( fp );
        }
        return listaFacturaProductos;
    }

}
