package repository;

import entity.Estudiante;
import java.util.List;

public interface EstudianteRepository {

    void save( Estudiante e ); // ver que pasa con carrera
    List<Estudiante> findAllOrderByDocumento( );
    Estudiante findByLibreta( int libreta);
    List<Estudiante> findAllByGenero( String genero );
    List<Estudiante> findAllByCarreraAndCiudad( int idCarrera, String ciudad);

}
