package repository;

import entity.CarreraEstudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;

public class CarreraEstudianteRepositoryImpl implements CarreraEstudianteRepository{

	private final EntityManager em;
	private static final Logger logger = LoggerFactory.getLogger(CarreraEstudianteRepositoryImpl.class);
	private static CarreraEstudianteRepositoryImpl instance;

	private CarreraEstudianteRepositoryImpl () {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
		this.em = emf.createEntityManager();
	}

	public static CarreraEstudianteRepositoryImpl getInstance( ) {
		if ( instance == null )
			instance = new CarreraEstudianteRepositoryImpl();
		return instance;
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
			logger.info("Didn't find result for idCarrera = " + idCarrera + " and idEstudiante = " + idEstudiante);
			return null;
		}
	}

	@Override
	public boolean exist ( CarreraEstudiante ce ) {
		return this.em.find( CarreraEstudiante.class, ce.getPk()) != null;
	}

}
