package repository;

import entity.CarreraEstudiante;

public interface CarreraEstudianteRepository {

	CarreraEstudiante findByIdCarreraAndIdEstudiante(int idCarrera, int idEstudiante);
	boolean exist ( CarreraEstudiante ce );
}
