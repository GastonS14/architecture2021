package repository;

import entity.Carrera;
import entity.Estudiante;

import java.util.ArrayList;

public interface EstudianteRepository {

    void save( Estudiante e ); // ver que pasa con carrera
    ArrayList<Estudiante> findAll( );
    Estudiante findByLibreta( int libreta);
    ArrayList<Estudiante> findAllByGenero( String genero );
    ArrayList<Estudiante> findAllByCarreraAndCiudad( int idCarrera, String ciudad);

}
