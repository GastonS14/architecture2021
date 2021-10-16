package repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class BaseRepository <Entity> implements Repository<Entity>{

    private final EntityManager em;
    private final Class<Entity> classType;

    public BaseRepository( Class<Entity> entity ) {
        this.em = Persistence.createEntityManagerFactory("Integrador3").createEntityManager();
        this.classType =  entity;
    }

    public Entity find( int id ) {
        return this.em.find( this.classType, id );
    }

    @Override
    public Entity save( Entity e ) {
        this.em.getTransaction().begin();
        this.em.persist( e );
        this.em.getTransaction().commit();
        return e;
    }

    @Override
    public Entity update( Entity e ) {
        this.em.getTransaction().begin();
        this.em.merge( e );
        this.em.getTransaction().commit();
        return e;
    }

}
