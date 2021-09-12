package entity;

public class Producto {
    private String nombre;
    private int valor;
    private int idProducto;

    public Producto(int idProducto, String nombre, int valor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Producto " +
                "nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", idProducto=" + idProducto;
    }
}
