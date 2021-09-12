package dto;

public class DTOcliente {
    private int idCliente;
    private String nombre;
    private String email;
    private Integer valorFacturacion;

    /** Usado para devolver los resultados del ejercicio 4 */
    public DTOcliente(int id,String nombre, String email, int valor) {
        this.idCliente = id;
        this.nombre = nombre;
        this.email = email;
        this.valorFacturacion = valor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getValorFacturacion() {
        return valorFacturacion;
    }

    @Override
    public String toString() {
        return
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", valorFacturacion=" + valorFacturacion
                ;
    }
}
