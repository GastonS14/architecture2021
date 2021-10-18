package repository;

import entity.Carrera;
import entity.CarreraEstudiante;
import entity.Estudiante;

import java.time.LocalDate;
import java.util.List;

public interface EstudianteRepository {


    List<Estudiante> findAllOrderByDocumento();
    Estudiante findByLibreta( int libreta);
    List<Estudiante> findAllByGenero( String genero );
    List<Estudiante> findAllByCarreraAndCiudad( int idCarrera, String ciudad);
    List<Estudiante> findAll();
    List<Estudiante> findAllOrderBy(String attribute, String sortOrder);
    List getAllCities();
    List<Estudiante> getStudentsByFilter(String genero, String ciudad);
}
