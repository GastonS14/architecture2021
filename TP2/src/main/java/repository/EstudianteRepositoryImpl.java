package repository;

import entity.CarreraEstudiante;
import entity.Estudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {

    private final EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(EstudianteRepositoryImpl.class);

    public EstudianteRepositoryImpl ( ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
        this.em = emf.createEntityManager();
    }

    @Override
    public void save (Estudiante e) {
        this.em.getTransaction().begin();
        if ( this.exist( e ) ) {
            this.em.merge(e);
        }else
            this.em.persist( e );
        this.em.getTransaction().commit();
    }

    private boolean exist ( Estudiante e ) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.documento = :doc";
        Query q = this.em.createQuery(jpql, Estudiante.class);
        q.setParameter("doc", e.getDocumento());
        try {
            q.getSingleResult();
            return true;
        } catch ( NoResultException exc ) {
            return false;
        }
    }

    @Override
    public Estudiante findByDocumento ( int doc ) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.documento = :doc";
        Query q = this.em.createQuery( jpql, Estudiante.class );
        q.setParameter( "doc", doc);
        try {
            return (Estudiante) q.getSingleResult();
        } catch (NoResultException e) {
            logger.info("Didn't find result for documento: " + doc);
            return null;
        }
    }

    @Override
    public Estudiante findByLibreta(int libreta) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.libretaUniversitaria = :libreta";
        Query q = this.em.createQuery(jpql);
        q.setParameter( "libreta", libreta );
        try {
            return (Estudiante) q.getSingleResult();
        } catch (NoResultException e) {
            logger.info("Didn't find result for libreta: " + libreta);
            return null;
        }
    }

    @Override
    public List<Estudiante> findAllOrderByDocumento() {
        String jpql = "SELECT e FROM Estudiante e ORDER BY e.documento";
        Query q = this.em.createQuery( jpql );
        return (List<Estudiante>) q.getResultList();
    }

    @Override
    public List<Estudiante> findAllByGenero(String genero) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        Query q = this.em.createQuery( jpql );
        q.setParameter( "genero", genero );
        return (List<Estudiante>) q.getResultList();
    }

    // recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @Override
    public List<Estudiante> findAllByCarreraAndCiudad( int idCarrera, String ciudad) {
        String jpql = """
                 SELECT e 
                 FROM Estudiante e 
                 JOIN CarreraEstudiante c 
                 ON e.documento = c.estudiante.documento 
                 WHERE e.ciudadResidencia = :ciudad
                 AND c.carrera.id_carrera = :idCarrera
                 """.trim();
        Query q = this.em.createQuery(jpql, Estudiante.class);
        q.setParameter( "ciudad", ciudad );
        q.setParameter( "idCarrera", idCarrera );
        return (List<Estudiante>) q.getResultList();
    }

}
