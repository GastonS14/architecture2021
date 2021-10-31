package entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table( name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;
    @Column private String nombre;
    @Column private String apellido;
    @OneToMany( mappedBy = "cliente")
    private ArrayList<Venta> compras;

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ArrayList<Venta> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Venta> compras) {
        this.compras = compras;
    }
}
