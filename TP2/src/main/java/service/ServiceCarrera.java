package service;

import repository.CarreraRepository;
import repository.CarreraRepositoryImpl;

public class ServiceCarrera {
    private CarreraRepository repository;

    public ServiceCarrera () {
        this.repository = new CarreraRepositoryImpl();
    }
}
