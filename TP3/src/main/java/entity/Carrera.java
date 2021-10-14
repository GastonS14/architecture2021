package entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.CarreraEstudianteRepository;
import repository.CarreraEstudianteRepositoryImpl;
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

    private static CarreraRepository repository = CarreraRepositoryImpl.getInstance();
    private static CarreraEstudianteRepository repositoryCE = CarreraEstudianteRepositoryImpl.getInstance();
    private static final Logger logger = LoggerFactory.getLogger( Carrera.class );

    public Carrera () {

    }

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

    public void setNombre ( String nombre ) {
        this.nombre = nombre;
        repository.save( this );
    }

    public boolean addStudent (Estudiante e, LocalDate fIngreso, LocalDate fEgreso ) {
        CarreraEstudiante ce = new CarreraEstudiante(this,e, fIngreso,fEgreso);
        if ( !repositoryCE.exist(ce) ) {
            this.estudianteCarreras.add(ce);
            repository.save( this );
            return true;
        }
        logger.info("There's already been a student with document: " + e.getDocumento() +
                " and career id: " + this.id_carrera);
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
