package service;

import entity.Carrera;
import entity.CarreraEstudiante;
import entity.Estudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.*;
import java.time.LocalDate;
import java.util.List;

public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;
    private final CarreraEstudianteRepository carreraEstudianteRepository;
    private static final Logger logger = LoggerFactory.getLogger(EstudianteService.class);

    public EstudianteService(){
        this.estudianteRepository = new EstudianteRepositoryImpl();
        this.carreraRepository = new CarreraRepositoryImpl();
        this.carreraEstudianteRepository = new CarreraEstudianteRepositoryImpl();
    }

    public void save(Estudiante e) {
        this.estudianteRepository.save( e );
    }

    public List<Estudiante> findAllOrderByDocumento() {
        return estudianteRepository.findAllOrderByDocumento();
    }

    public Estudiante findByLibreta(int libreta) {
        return estudianteRepository.findByLibreta(libreta);
    }

    public Estudiante findByDocumento(int documento) {
        return estudianteRepository.findByDocumento(documento);
    }

    public List<Estudiante> findAllByGenero(String genero) {
        return estudianteRepository.findAllByGenero(genero);
    }

    public List<Estudiante> findAllByCarreraAndCiudad(int idCarrera, String ciudad) {
        return this.estudianteRepository.findAllByCarreraAndCiudad(idCarrera, ciudad);
    }

    public void removeCarrera( int idCarrera, int documento ){
        Carrera carrera = carreraRepository.findById( idCarrera );
        Estudiante estudiante = estudianteRepository.findByDocumento(documento);
        if(carrera != null) {
            if(estudiante != null)
                estudiante.removeCareer( new CarreraEstudiante(carrera,estudiante,null,null));
            else
                logger.info("The student doesn't exists");
        } else {
            logger.info("The career doesn't exists");
        }
    }

}
