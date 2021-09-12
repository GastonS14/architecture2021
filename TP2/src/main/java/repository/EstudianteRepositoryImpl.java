package repository;

import entity.Estudiante;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;


public class EstudianteRepositoryImpl implements EstudianteRepository{
    private final EntityManager em;

    public EstudianteRepositoryImpl ( ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
        this.em = emf.createEntityManager();
        emf.close();
    }

    @Override
    public void save(Estudiante e) {
        em.persist(e);
    }

    @Override
    public ArrayList<Estudiante> findAll() {
        String jpql = "SELECT e FROM Estudiante e";
        Query q = this.em.createQuery( jpql );
        ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) q.getResultList();
        this.em.close();
        return estudiantes;
    }

    @Override
    public Estudiante findByLibreta(int libreta) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.libretaUniversitaria = :libreta";
        Query q = this.em.createQuery( jpql, Estudiante.class );
        q.setParameter( "libreta", libreta );
        Estudiante estudiante = (Estudiante) q.getSingleResult();
        this.em.close();
        return estudiante;
    }

    @Override
    public ArrayList<Estudiante> findAllByGenero(String genero) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        Query q = this.em.createQuery( jpql );
        q.setParameter( "genero", genero );
        ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) q.getResultList();
        this.em.close();
        return estudiantes;
    }

    @Override
    public ArrayList<Estudiante> findAllByCarreraAndCiudad( int idCarrera, String ciudad) {
        String jpql = "SELECT e FROM Estudiante e JOIN Carrera c WHERE e.ciudadResidencia = :ciudad " +
                 "AND c.id = :idCarrera";
        Query q = this.em.createQuery( jpql );
        q.setParameter( "ciudad", ciudad );
        q.setParameter( "idCarrera", idCarrera );
        ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) q.getResultList();
        this.em.close();
        return estudiantes;
    }

}
