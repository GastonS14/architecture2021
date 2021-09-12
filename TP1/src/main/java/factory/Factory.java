package factory;

import dao.*;

import java.sql.SQLException;

/**
 * Clase encargada de suministrar la creacion de DAOs y manejar la conexion a la DB.
 * Es singleton, es decir, habra una unica instancia de esta clase en el programa.
 */

public class Factory implements AbstractFactory {
    private final String tecnologia;
    private Conexion con;
    private static Factory f;

    private Factory ( String tecnologia ) {
        this.tecnologia = tecnologia;
    }

    /**
     * Dependiendo de la DB a utilizar, se setea el driver y la uri de la conexion.
     * @throws SQLException si la conexion falla
     */
    private void setConexion ( ) throws SQLException {
        String driver;
        String uri;
        if ( this.tecnologia.equalsIgnoreCase( "sql" ) ) {
            driver = "com.mysql.cj.jdbc.Driver";
            uri = "jdbc:mysql://localhost:3306/example_DB";
        } else {
            driver = "org.apache.derby.jdbc.EmbeddedDriver";
            uri = "jdbc:derby:MyDerbyDB;create=true";
        }
        con = Conexion.getInstance(driver, uri);
    }

    /**
     * Solo a traves de este metodo se obtiene una instancia de factory
     * @param tecnologia usada para determinar la tecnologia de base de datos a utilizarse
     * @return instancia del factory
     * @throws SQLException si la conexion falla
     */
    public static Factory getInstance ( String tecnologia ) throws SQLException {
        if ( f == null ) {
            f = new Factory(tecnologia);
            f.setConexion();
        }
        return f;
    }

    public Conexion getConexion () {
        return this.con;
    }

    /** Creacion de los DAO segun la tecnologia.
     *  En este caso solo se usara MySql, pero de usarse otra DB, se debera crear
     *  una clase abstracta para cada DAO y sus clases concretas por cada DB a usar.
     * @return instancia de la DaoCliente
     */
    public DAOcliente getDaoCliente ( ) {
        return new DAOclienteMySql( this.con );
    }

    public DAOfactura getDaoFactura () {
        return new DAOfacturaMySql( this.con );
    }

    public DAOproducto getDaoProducto () {
        return new DAOproductoMySql( this.con );
    }

    public DAOfactura_producto getDaoFacProd () {
        return new DAOfactura_productoMySql( this.con );
    }

}
