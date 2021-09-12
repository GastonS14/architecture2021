package service;

import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;

public class ServiceEstudiante {

    private EstudianteRepository repository;

    public ServiceEstudiante (){
        this.repository = new EstudianteRepositoryImpl();
    }

}
