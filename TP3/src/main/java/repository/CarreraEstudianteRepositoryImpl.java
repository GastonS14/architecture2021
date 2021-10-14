package repository;

import entity.CarreraEstudiante;
import javax.persistence.*;

public final class CarreraEstudianteRepositoryImpl implements CarreraEstudianteRepository{

	private final EntityManager em;
	private static CarreraEstudianteRepositoryImpl instance;

	private CarreraEstudianteRepositoryImpl () {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador3");
		this.em = emf.createEntityManager();
	}

	public static CarreraEstudianteRepositoryImpl getInstance( ) {
		if ( instance == null )
			instance = new CarreraEstudianteRepositoryImpl();
		return instance;
	}

	@Override
	public boolean exist ( CarreraEstudiante ce ) {
		return this.em.find( CarreraEstudiante.class, ce.getPk()) != null;
	}

}
