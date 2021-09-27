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
        Estudiante estudiante = estudianteRepository.findByDocumento(e.getId());
        if(estudiante == null){
            estudianteRepository.save(e);
        } else {
            estudianteRepository.update(e);
        }
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

    public void addCarrera (Carrera c, Estudiante e, LocalDate fechaIngreso, LocalDate fechaEgreso ){
        this.estudianteRepository.addCareer( c, e, fechaIngreso, fechaEgreso );
        this.estudianteRepository.update ( e );
    }

    /**
     * If you want to remove some career, first you need the student.
     * So in main, you should write something like this:
     *      se.removeCarrera( carrera, se.findByLibreta( 22 ) );
     * @param c career that you want to delete
     * @param e the student that you want to update
     */
    public void removeCarreraJuan (Carrera c, Estudiante e ){
        this.estudianteRepository.removeCareerJuan( c, e );
        this.estudianteRepository.update( e );
    }

    public void removeCarrera( int idCarrera, int documento ){
        Carrera carrera = carreraRepository.findById( idCarrera );
        Estudiante estudiante = estudianteRepository.findByDocumento(documento);
        if(carrera != null) {
            if(estudiante != null) {
                CarreraEstudiante carreraEstudiante = carreraEstudianteRepository.findByIdCarreraAndIdEstudiante(idCarrera, documento);
                this.estudianteRepository.removeCareer( estudiante, carreraEstudiante );
                this.estudianteRepository.update ( estudiante );
            } else {
                logger.info("The student doesn't exists");
            }
        } else {
            logger.info("The career doesn't exists");
        }
    }

}
