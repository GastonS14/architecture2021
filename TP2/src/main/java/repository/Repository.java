package repository;


public interface Repository <Entity> {

    public Entity find( int id);
    public Entity save( Entity e );
    public Entity update ( Entity e );

}
