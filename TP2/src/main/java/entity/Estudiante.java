package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "estudiante")
public class Estudiante {
    @Id
    private int documento;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(unique = true, nullable = false)
    private int libretaUniversitaria;
    @Column(nullable = false)
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudadResidencia;

    @OneToMany (mappedBy = "estudiante",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true) // this remove references
    private List<CarreraEstudiante> carreraEstudiante;

    public Estudiante (){}

    public Estudiante (int doc){
        this.documento = doc;
    }

    public Estudiante ( int doc, String nombre, String apellido, int edad, int libretaUniversitaria, String genero , String ciudadResidencia) {
        this.documento = doc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.libretaUniversitaria = libretaUniversitaria;
        this.ciudadResidencia = ciudadResidencia;
        this.carreraEstudiante = new ArrayList<>();
    }

    public int getId() {
        return this.documento;
    }

    public String getName ( ) {
        return this.nombre;
    }

    public boolean addCareer(CarreraEstudiante ce ) {
        if ( !this.carreraEstudiante.contains(ce) ) {
            this.carreraEstudiante.add(ce);
            return true;
    }
        return false;
    }

    public boolean removeCareer(CarreraEstudiante ce ) {
        return this.carreraEstudiante.remove( ce );
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "documento=" + documento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", libretaUniversitaria=" + libretaUniversitaria +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", ciudadResidencia='" + ciudadResidencia + '\'' +
                ", cantidadDeCarreras='" + this.carreraEstudiante.size() + '\'' +
                '}';
    }
}
