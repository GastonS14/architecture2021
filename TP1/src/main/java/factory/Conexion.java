package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String driver;
    private String uri;
    private String user = "root";
    private String password = "password";
    private Connection con;
    private static Conexion conexion;

    private Conexion ( String driver, String uri ) {
        this.driver = driver;
        this.uri = uri;
    }

    /**
     * Crea la coneccion si no ha sido creada o abre la coneccion si esta cerrada.
     */
    private void setConnection () {
        if ( con == null ) {
            try {
                Class.forName(driver).getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
            try {
                con = DriverManager.getConnection( uri, user, password);
                con.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
    }

    private void setDriver ( String driver ) {
        this.driver = driver;
    }

    private void setUri ( String uri ) {
        this.uri = uri;
    }

    /** Devuelve una unica instancia de Conexion.
     *  Patron singleton */
    public static Conexion getInstance ( String driver, String uri ) throws SQLException{
        if ( conexion == null ) {
            conexion = new Conexion(driver, uri);
        } else {
            conexion.setDriver ( driver );
            conexion.setUri ( uri );
        }
        conexion.setConnection();
        return conexion;
    }

    /** obtiene la conexion del driverManagger */
    public Connection getConnection () throws SQLException {
        if ( this.con == null || this.con.isClosed() ) {
            this.setConnection();
        }
        return this.con;
    }

    public void closeConnection () throws SQLException {
        this.con.close();
    }

    public void commit () throws SQLException{
        this.con.commit();
    }

}
