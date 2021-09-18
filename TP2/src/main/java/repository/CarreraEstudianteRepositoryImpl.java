package repository;

import dto.CarreraReportDto;
import entity.Carrera;
import entity.CarreraEstudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CarreraEstudianteRepositoryImpl implements CarreraEstudianteRepository{

	private final EntityManager em;

	public CarreraEstudianteRepositoryImpl () {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
		this.em = emf.createEntityManager();
	}

	public void save(CarreraEstudiante carreraEstudiante) {
		em.getTransaction().begin();
		em.persist(carreraEstudiante);
		em.getTransaction().commit();
	}

	// Generar un reporte de las carreras, que para cada carrera incluya información de los
	// inscriptos y egresados por año.
	// Se deben ordenar las carreras alfabéticamente y fechaIngreso Desc
	public CarreraReportDto report2() {
		String jpql = """
                SELECT new CarreraReportDto() 
                FROM Carrera c
                JOIN CarreraEstudiante ce
                ON c.id_carrera = ce.carrera.id_carrera
                JOIN Estudiante e
                ON ce.estudiante.documento = e.documento
				""".trim();
		return new CarreraReportDto();
	}
}
