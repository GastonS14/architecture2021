package repository;

import dto.CarreraDto;
import entity.Carrera;
import java.util.List;

public interface CarreraRepository {

    void save( Carrera c );
    List<Carrera> findAll();
    List<CarreraDto> findAllByInscriptosOrderByCount();
    List<Carrera> report();
}
