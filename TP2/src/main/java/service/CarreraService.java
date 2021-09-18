package service;

import dto.CarreraDto;
import entity.Carrera;
import repository.CarreraRepository;
import repository.CarreraRepositoryImpl;

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
}
