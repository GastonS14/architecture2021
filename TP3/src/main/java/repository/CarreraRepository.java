package repository;

import dto.CarreraDto;
import dto.CarreraReportDto;
import entity.Carrera;
import entity.CarreraEstudiante;
import entity.Estudiante;

import java.time.LocalDate;
import java.util.List;

public interface CarreraRepository {

    List<Carrera> findAll();
    List<CarreraDto> findAllByInscriptosOrderByCount();
    List<CarreraReportDto> report();
    Carrera findByName ( String name );
}
