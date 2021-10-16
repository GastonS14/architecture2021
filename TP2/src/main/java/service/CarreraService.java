package service;

import dto.CarreraDto;
import dto.CarreraReportDto;
import entity.Carrera;
import repository.*;
import java.util.List;

public final class CarreraService {

    private static final CarreraRepositoryImpl carreraRepository = CarreraRepositoryImpl.getInstance();

    private CarreraService() { }

    public static void save(Carrera carrera) {
        carreraRepository.save(carrera);
    }

    public static List<Carrera> findAll() {
        return carreraRepository.findAll();
    }

    public static List<CarreraDto> findAllByInscriptosOrderByCount() {
        return carreraRepository.findAllByInscriptosOrderByCount();
    }

    public static Carrera findById( int idCarrera ) {
       return carreraRepository.findById( idCarrera );
    }

    public static Carrera findByName ( String name ) {
        return carreraRepository.findByName( name );
    }

    public static List<CarreraReportDto> report () {
        return carreraRepository.report();
    }
}
