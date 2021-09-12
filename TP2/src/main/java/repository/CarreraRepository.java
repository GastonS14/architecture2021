package repository;

import entity.Carrera;
import entity.Estudiante;

import java.util.ArrayList;

public interface CarreraRepository {

    void save( Carrera c );
    ArrayList<Carrera> findAllByInscriptos(); // ver el order by
    ArrayList< Carrera > report ();
}
