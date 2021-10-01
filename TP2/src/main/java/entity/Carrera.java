package entity;

import repository.CarreraEstudianteRepository;
import repository.CarreraRepository;
import repository.CarreraRepositoryImpl;
import javax.persistence.*;
import java.time.LocalDate;
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
            orphanRemoval = true
    ) // this remove references
    private List<CarreraEstudiante> estudianteCarreras;
    @Transient
    private CarreraRepository repository;
    @Transient
    private CarreraEstudianteRepository repositoryCE;

    public Carrera () {}

    public Carrera( String nombre) {
        this.nombre = nombre;
        this.estudianteCarreras = new ArrayList<>();
        this.repository = CarreraRepositoryImpl.getInstance();
    }

    public int getId() {
        return this.id_carrera;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre ( String nombre ) {
        this.nombre = nombre;
        this.repository.save( this );
    }

    public boolean addStudent (Estudiante e, LocalDate fIngreso, LocalDate fEgreso ) {
        CarreraEstudiante ce = new CarreraEstudiante(this,e, fIngreso,fEgreso);
        if ( !this.repositoryCE.exist(ce) ) {
            this.estudianteCarreras.add(ce);
            this.repository.save( this );
            return true;
        }
        return false;
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
