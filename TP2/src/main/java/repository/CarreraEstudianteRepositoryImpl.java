package repository;

import entity.CarreraEstudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class CarreraEstudianteRepositoryImpl implements CarreraEstudianteRepository{

	private final EntityManager em;
	private static final Logger logger = LoggerFactory.getLogger(CarreraEstudianteRepositoryImpl.class);

	public CarreraEstudianteRepositoryImpl () {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
		this.em = emf.createEntityManager();
	}

	public CarreraEstudiante findByIdCarreraAndIdEstudiante(int idCarrera, int idEstudiante) {
		String query = """
				SELECT ce  
				FROM CarreraEstudiante ce 
				WHERE ce.carreraEstudiantePk.idCarrera = :idCarrera 
				AND ce.carreraEstudiantePk.idEstudiante = :idEstudiante""".trim();
		Query q = this.em.createQuery( query );
		q.setParameter( "idCarrera", idCarrera );
		q.setParameter( "idEstudiante", idEstudiante );
		try {
			return ( CarreraEstudiante ) q.getSingleResult();
		} catch(NoResultException e) {
			logger.info("Not result founded for idCarrera = " + idCarrera + " and idEstudiante = " + idEstudiante);
			return null;
		}
	}

}
