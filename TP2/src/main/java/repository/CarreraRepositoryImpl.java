package repository;

import entity.Carrera;
import entity.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class CarreraRepositoryImpl implements CarreraRepository {

    private EntityManager em;

    public CarreraRepositoryImpl () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
        this.em = emf.createEntityManager();
        emf.close();
    }
    @Override
    public void save( Carrera c ) {
        this.em.persist( c );
    }

    @Override
    public ArrayList<Carrera> findAllByInscriptos() {
        //String jpql = "SELECT c FROM Carrera c WHERE c. "
        return null;
    }

    @Override
    public ArrayList<Carrera> report() {
        return null;
    }

}
