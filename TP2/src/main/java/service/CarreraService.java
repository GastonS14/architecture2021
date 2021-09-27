package service;

import dto.CarreraDto;
import dto.CarreraReportDto;
import entity.Carrera;
import entity.CarreraEstudiante;
import entity.Estudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.*;
import java.time.LocalDate;
import java.util.List;

public class CarreraService {

    private final CarreraRepository carreraRepository;
    private final EstudianteRepository estudianteRepository;
    private final CarreraEstudianteRepository carreraEstudianteRepository;
    private static final Logger logger = LoggerFactory.getLogger(CarreraService.class);

    public CarreraService() {
        this.carreraRepository = new CarreraRepositoryImpl();
        this.estudianteRepository = new EstudianteRepositoryImpl();
        this.carreraEstudianteRepository = new CarreraEstudianteRepositoryImpl();
    }

    public void save(Carrera carrera) {
        Carrera c = carreraRepository.findById(carrera.getId());
        if(c == null){
            this.carreraRepository.save(carrera);
        } else {
            this.carreraRepository.update(carrera);
        }
    }

    public List<Carrera> findAll() {
        return this.carreraRepository.findAll();
    }

    public List<CarreraDto> findAllByInscriptosOrderByCount() {
        return this.carreraRepository.findAllByInscriptosOrderByCount();
    }

    public void addStudent ( Carrera c, Estudiante e, LocalDate fechaIngreso, LocalDate fechaEgreso ){
        this.carreraRepository.addStudent( c, e, fechaIngreso, fechaEgreso );
        this.carreraRepository.update ( c );
    }

    public void removeStudent( int idCarrera, int documento ){
        Carrera carrera = carreraRepository.findById( idCarrera );
        Estudiante estudiante = estudianteRepository.findByDocumento(documento);
        if(carrera != null) {
            if(estudiante != null) {
                CarreraEstudiante carreraEstudiante = carreraEstudianteRepository.findByIdCarreraAndIdEstudiante(idCarrera, documento);
                this.carreraRepository.removeStudent( carrera, carreraEstudiante );
                this.carreraRepository.update ( carrera );
            } else {
                logger.info("The student doesn't exists");
            }
        } else {
            logger.info("The career doesn't exists");
        }
    }

    /**
     * @param idCarrera career identification
     * @return some career that match with idCarrera
     */
    public Carrera findById( int idCarrera ) {
       return this.carreraRepository.findById( idCarrera );
    }

    public Carrera findByName ( String name ) {
        return this.carreraRepository.findByName( name );
    }

    public List<CarreraReportDto> report () {
        return this.carreraRepository.report();
    }
}
