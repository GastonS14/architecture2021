package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table ( name = "carrera")
public class Carrera {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private int id_carrera;

    @Column ( unique = true, nullable = false )
    private String nombre;

    @OneToMany (mappedBy = "carrera",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true) // this remove references
    private List<CarreraEstudiante> estudianteCarreras;

    public Carrera () {}

    public Carrera( String nombre) {
        this.nombre = nombre;
        this.estudianteCarreras = new ArrayList<>();
    }

    public int getId() {
        return this.id_carrera;
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean addStudent ( CarreraEstudiante ce ) {
        if (!this.estudianteCarreras.contains(ce) ) {
            this.estudianteCarreras.add(ce);
            return true;
        }
        return false;
    }

    public boolean removeStudent ( CarreraEstudiante ce ) {
        return this.estudianteCarreras.remove( ce );
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
