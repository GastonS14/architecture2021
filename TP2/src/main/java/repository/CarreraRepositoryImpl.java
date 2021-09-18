package repository;

import dto.CarreraDto;
import entity.Carrera;
import entity.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {

    private final EntityManager em;

    public CarreraRepositoryImpl () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
        this.em = emf.createEntityManager();
    }

    @Override
    public void save(Carrera carrera) {
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
    }

    @Override
    public List<Carrera> findAll() {
        em.getTransaction().begin();
        String jpql = "SELECT c FROM Carrera c";
        Query q = this.em.createQuery( jpql );
        List<Carrera> carreras = q.getResultList();
        em.getTransaction().commit();
        return carreras;
    }

    @Override
    public List<CarreraDto> findAllByInscriptosOrderByCount() {
        this.em.getTransaction().begin();
        String sql = """
                SELECT new CarreraDto( c.id_carrera, c.nombre, count(*) )
                FROM Carrera c 
                JOIN CarreraEstudiante ce 
                ON c.id_carrera = ce.carrera.id_carrera
                GROUP BY c.id_carrera, c.nombre
                ORDER BY 3 DESC
                """.trim();
        Query q = this.em.createQuery(sql, CarreraDto.class);
        List<CarreraDto> carreraDto = q.getResultList();
        em.getTransaction().commit();
        return carreraDto;
    }

    @Override
    public List<Carrera> report() {
        return null;
    }

}
