package repository;

import dto.CarreraDto;
import dto.CarreraReportDto;
import entity.Carrera;
import entity.CarreraEstudiante;
import entity.Estudiante;

import java.time.LocalDate;
import java.util.List;

public interface CarreraRepository {

    void save( Carrera c );
    List<Carrera> findAll();
    List<CarreraDto> findAllByInscriptosOrderByCount();
    List<CarreraReportDto> report();
    Carrera findById(int idCarrera);
    Carrera findByName ( String name );
}
