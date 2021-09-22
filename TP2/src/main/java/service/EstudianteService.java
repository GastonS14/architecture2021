package service;

import entity.Carrera;
import entity.Estudiante;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public class EstudianteService {

    private final EstudianteRepository repository;

    public EstudianteService(){
        this.repository = new EstudianteRepositoryImpl();
    }

    public void save(Estudiante e) {
        repository.save(e);
    }

    public List<Estudiante> findAllOrderByDocumento() {
        return repository.findAllOrderByDocumento();
    }

    public Estudiante findByLibreta(int libreta) {
        return repository.findByLibreta(libreta);
    }

    public List<Estudiante> findAllByGenero(String genero) {
        return repository.findAllByGenero(genero);
    }

    public List<Estudiante> findAllByCarreraAndCiudad(int idCarrera, String ciudad) {
        return this.repository.findAllByCarreraAndCiudad(idCarrera, ciudad);
    }

    public void addCarrera (Carrera c, Estudiante e, LocalDate fechaIngreso, LocalDate fechaEgreso ){
        this.repository.addCareer( c, e, fechaIngreso, fechaEgreso );
        this.repository.update ( e );
    }

    /**
     * If you want to remove some career, first you need the student.
     * So in main, you should write something like this:
     *      se.removeCarrera( carrera, se.findByLibreta( 22 ) );
     * @param c career that you want to delete
     * @param e the student that you want to update
     */
    public void removeCarrera (Carrera c, Estudiante e ){
        this.repository.removeCareer( c, e );
        this.repository.update( e );
    }



}
