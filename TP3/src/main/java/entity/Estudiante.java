package entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.*;
import repository.EstudianteRepositoryImpl;
import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany ( mappedBy = "estudiante",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true) // this remove references
    private List<CarreraEstudiante> carreraEstudiante;

    private static final Logger logger = LoggerFactory.getLogger( Estudiante.class );
    private static final EstudianteRepositoryImpl repository = EstudianteRepositoryImpl.getInstance();
    private static final CarreraEstudianteRepository repositoryCE = CarreraEstudianteRepositoryImpl.getInstance();

    public Estudiante (){ }

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

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
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

    public int getLibretaUniversitaria() {
        return libretaUniversitaria;
    }

    public void setLibretaUniversitaria(int libretaUniversitaria) {
        this.libretaUniversitaria = libretaUniversitaria;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    /**
     * Dilema: Si en el main se instancia un Estudiante, tendra la lista vacia de carreras, pero puede que
     *  ese id con el que se instanció este en la db. Entonces,¿ Cómo manejamos el add y el remove ? Ya que puede
     *  que ese idEstudiante tenga carreras asociadas...
     *  Nosotros decidimos preguntar en la base e ignorar la lista que tiene el objeto. Esto hace que funcione
     *  en todos los casos. El problema es que no sera performante en los casos que la lista este actualizada con
     *  respecto a la db.
     */
    public boolean addCareer(Carrera c, LocalDate fIngreso, LocalDate fEgreso ) {
        CarreraEstudiante ce = new CarreraEstudiante(c,this, fIngreso,fEgreso);
        if ( !repositoryCE.exist(ce) ) {
            this.carreraEstudiante.add(ce);
            repository.save( this );
            return true;
        }
        logger.info("There's already been a career with id: " + c.getId() + " and student document " + this.documento);
        return false;
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
