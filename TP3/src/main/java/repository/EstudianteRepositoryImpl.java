package repository;

import entity.Estudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.util.List;

public final class EstudianteRepositoryImpl implements EstudianteRepository {

    private final EntityManager em = Persistence.createEntityManagerFactory("Integrador3").createEntityManager();
    private static final Logger logger = LoggerFactory.getLogger(EstudianteRepositoryImpl.class);
    private static EstudianteRepositoryImpl instance;

    private EstudianteRepositoryImpl ( ){}

    public static EstudianteRepositoryImpl getInstance( ) {
        if ( instance == null )
            instance = new EstudianteRepositoryImpl();
        return instance;
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
        return this.em.find( Estudiante.class, e.getDocumento() ) != null;
    }

    @Override
    public Estudiante findByDocumento ( int doc ) {
        return this.em.find( Estudiante.class, doc);
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
        String jpql =
                 "SELECT e "+
                 "FROM Estudiante e "+
                 "JOIN CarreraEstudiante c "+
                 "ON e.documento = c.estudiante.documento "+
                 "WHERE e.ciudadResidencia = :ciudad "+
                 "AND c.carrera.id_carrera = :idCarrera ";
        Query q = this.em.createQuery(jpql, Estudiante.class);
        q.setParameter( "ciudad", ciudad );
        q.setParameter( "idCarrera", idCarrera );
        return (List<Estudiante>) q.getResultList();
    }

    @Override
    public List<Estudiante> findAll() {
        String query = "SELECT e FROM Estudiante e";
        Query q = this.em.createQuery( query, Estudiante.class );
        return q.getResultList();
    }

}
