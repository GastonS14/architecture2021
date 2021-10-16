package repository;

import entity.Estudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.util.List;

public final class EstudianteRepositoryImpl extends BaseRepository<Estudiante> implements EstudianteRepository {

    private final EntityManager em = Persistence.createEntityManagerFactory("Integrador2").createEntityManager();
    private static final Logger logger = LoggerFactory.getLogger(EstudianteRepositoryImpl.class);
    private static EstudianteRepositoryImpl instance;

    private EstudianteRepositoryImpl ( ){
        super( Estudiante.class );
    }

    public static EstudianteRepositoryImpl getInstance( ) {
        if ( instance == null )
            instance = new EstudianteRepositoryImpl();
        return instance;
    }

    public Estudiante save (Estudiante e) {
        if ( this.exist( e ) )
            return super.save(e);
        return super.update( e );
    }

    private boolean exist ( Estudiante e ) {
        return this.find( e.getDocumento() ) != null;
    }

    public Estudiante findByDocumento ( int doc ) {
        return this.find(doc);
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
