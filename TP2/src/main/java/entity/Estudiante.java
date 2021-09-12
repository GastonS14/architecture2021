package entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Estudiante {
    @Id
    private int documento;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int libretaUniversitaria;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudadResidencia;

    @ManyToMany ( fetch = FetchType.LAZY )
    private ArrayList<Carrera> carreras;

    public Estudiante (){}

    public Estudiante ( int doc, String nombre, String apellido, int edad, int libretaUniversitaria, String genero ) {
        this.documento = doc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.libretaUniversitaria = libretaUniversitaria;
    }


}
