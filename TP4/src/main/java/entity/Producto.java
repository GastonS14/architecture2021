package entity;

import javax.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;
    @Column
    private String nombre;
    @Column
    private double precio;
    @Column
    private int stock;

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
