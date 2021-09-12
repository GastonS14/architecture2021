package entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Carrera {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private int id;
    @Column
    private String nombre;

    @ManyToMany ( mappedBy = "carreras", fetch = FetchType.LAZY )
    private ArrayList<Estudiante> estudiantes;


    public Carrera () {}



}
