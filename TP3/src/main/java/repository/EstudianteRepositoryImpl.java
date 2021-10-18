package repository;

import entity.Estudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.util.List;

public final class EstudianteRepositoryImpl extends BaseRepository<Estudiante> implements EstudianteRepository {

    private final EntityManager em = Persistence.createEntityManagerFactory("Integrador3").createEntityManager();
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
            return super.update( e );
        return super.save( e );
    }

    private boolean exist ( Estudiante e ) {
        return this.find( e.getDocumento() ) != null;
    }

    public Estudiante findByDocumento ( int doc ) {
        return this.find( doc );
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
    public List<Estudiante> findAllOrderByDocumento( ) {
        String jpql = "SELECT * FROM estudiante e ORDER BY e.documento";
        Query q = this.em.createNativeQuery( jpql );
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

    @Override
    public List<Estudiante> findAllOrderBy( String attribute, String sortOrder ) {
        String query = "SELECT e FROM Estudiante e ORDER BY "+"e."+ attribute +" "+ sortOrder;
        Query q = this.em.createQuery( query );
        return (List<Estudiante>) q.getResultList();
    }

    @Override
    public List<String> getAllCities() {
        String query = "SELECT distinct e.ciudadResidencia FROM Estudiante e";
        Query q = this.em.createQuery( query, String.class );
        return q.getResultList();
    }

    @Override
    public List<Estudiante> getStudentsByFilter(String genero, String ciudad) {
        String query = "SELECT e FROM Estudiante e WHERE e.genero =:genero and e.ciudadResidencia= :ciudad";
        Query q = this.em.createQuery( query, Estudiante.class );
        q.setParameter("genero", genero );
        q.setParameter( "ciudad", ciudad);
        return q.getResultList();
    }

}
