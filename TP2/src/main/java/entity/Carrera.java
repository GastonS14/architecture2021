package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrera {

    @Id
    // @GeneratedValue( strategy = GenerationType.AUTO )
    private int id_carrera;

    @Column
    private String nombre;

    @OneToMany (mappedBy = "carrera", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<CarreraEstudiante> estudianteCarreras;

    public Carrera () {}

    public Carrera(int id_carrera, String nombre) {
        this.id_carrera = id_carrera;
        this.nombre = nombre;
    }

    public int getId() {
        return this.id_carrera;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + id_carrera +
                ", nombre='" + nombre + '\'' +
                ", estudianteCarreras=" + estudianteCarreras +
                '}';
    }
}
