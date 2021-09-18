package service;

import entity.Estudiante;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;

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



}
