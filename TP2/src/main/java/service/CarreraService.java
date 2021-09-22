package service;

import dto.CarreraDto;
import dto.CarreraReportDto;
import entity.Carrera;
import entity.Estudiante;
import repository.CarreraRepository;
import repository.CarreraRepositoryImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarreraService {
    private final CarreraRepository repository;

    public CarreraService() {
        this.repository = new CarreraRepositoryImpl();
    }

    public void save(Carrera carrera) {
        this.repository.save(carrera);
    }

    public List<Carrera> findAll() {
        return this.repository.findAll();
    }

    public List<CarreraDto> findAllByInscriptosOrderByCount() {
        return this.repository.findAllByInscriptosOrderByCount();
    }

    public void addStudent ( Carrera c, Estudiante e, LocalDate fechaIngreso, LocalDate fechaEgreso ){
        this.repository.addStudent( c, e, fechaIngreso, fechaEgreso );
        this.repository.update ( c );
    }

    public void removeStudent( Carrera c, Estudiante e ){
        this.repository.removeStudent( c, e );
        this.repository.update ( c );
    }

    /**
     * Career has auto-generated id, so probably we would never use this method.
     * You should use findByName instead.
     * @param idCarrera
     * @return some career that match with idCarrera
     */
    public Carrera findById( int idCarrera ) {
       return this.repository.findById( idCarrera );
    }

    public Carrera findByName ( String name ) {
        return this.repository.findByName( name );
    }

    public List<CarreraReportDto> report () {
        ArrayList report = (ArrayList) this.repository.report();
        return report;
    }
}
