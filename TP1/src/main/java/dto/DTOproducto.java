package dto;

public class DTOproducto {
    private final int idProducto;
    private final int recaudacion;

    public DTOproducto ( int idProducto, int recaudacion ) {
        this.idProducto = idProducto;
        this.recaudacion = recaudacion;
    }

    public int getIdProducto ( ) {
        return this.idProducto;
    }

    public int getRecaudacion( ) {
        return this.recaudacion;
    }

    @Override
    public String toString() {
        return "Producto: " +
                "idProducto=" + idProducto +
                ", recaudacion=" + recaudacion ;
    }
}
