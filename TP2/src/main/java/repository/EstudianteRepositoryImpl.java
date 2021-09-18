package repository;

import entity.Estudiante;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


public class EstudianteRepositoryImpl implements EstudianteRepository {

    private final EntityManager em;

    public EstudianteRepositoryImpl ( ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
        this.em = emf.createEntityManager();
    }

    @Override
    public void save(Estudiante e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    @Override
    public List<Estudiante> findAllOrderByDocumento() {
        em.getTransaction().begin();
        String jpql = "SELECT e FROM Estudiante e ORDER BY e.documento";
        Query q = this.em.createQuery( jpql );
        List<Estudiante> estudiantes = q.getResultList();
        em.getTransaction().commit();
        return estudiantes;
    }

    @Override
    public Estudiante findByLibreta(int libreta) {
        em.getTransaction().begin();
        String jpql = "SELECT e FROM Estudiante e WHERE e.libretaUniversitaria = :libreta";
        Query q = this.em.createQuery(jpql);
        q.setParameter( "libreta", libreta );
        Estudiante estudiante = (Estudiante) q.getSingleResult();
        em.getTransaction().commit();
        return estudiante;
    }

    @Override
    public List<Estudiante> findAllByGenero(String genero) {
        em.getTransaction().begin();
        String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        Query q = this.em.createQuery( jpql );
        q.setParameter( "genero", genero );
        List<Estudiante> estudiantes = q.getResultList();
        em.getTransaction().commit();
        return estudiantes;
    }

    // recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @Override
    public List<Estudiante> findAllByCarreraAndCiudad( int idCarrera, String ciudad) {
        this.em.getTransaction().begin();
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
        List<Estudiante> estudiantes = q.getResultList();
        em.getTransaction().commit();
        return estudiantes;
    }

    /*
    @Override
    public Address findById(int id) {
        em.getTransaction().begin();
        String jpql = "SELECT e FROM Address e WHERE e.id = :id";
        Query q = this.em.createQuery( jpql, Address.class );
        q.setParameter( "id", id );
        Address address = (Address) q.getSingleResult();
        return address;
    }
     */

}
