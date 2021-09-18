package service;

import entity.CarreraEstudiante;
import repository.CarreraEstudianteRepository;
import repository.CarreraEstudianteRepositoryImpl;

public class CarreraEstudianteService {

	private final CarreraEstudianteRepository carreraEstudianteRepository;

	public CarreraEstudianteService() {
		this.carreraEstudianteRepository = new CarreraEstudianteRepositoryImpl();
	}

	public void save(CarreraEstudiante carreraEstudiante) {
		carreraEstudianteRepository.save(carreraEstudiante);
	}
}
