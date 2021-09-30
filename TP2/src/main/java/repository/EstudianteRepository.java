package repository;

import entity.Carrera;
import entity.CarreraEstudiante;
import entity.Estudiante;

import java.time.LocalDate;
import java.util.List;

public interface EstudianteRepository {

    void save( Estudiante e );
    List<Estudiante> findAllOrderByDocumento( );
    Estudiante findByLibreta( int libreta);
    Estudiante findByDocumento( int doc );
    List<Estudiante> findAllByGenero( String genero );
    List<Estudiante> findAllByCarreraAndCiudad( int idCarrera, String ciudad);

}
