package entity;

import javax.persistence.*;

@Entity
@Table( name = "venta_producto")
public class VentaProducto {

    @EmbeddedId
    private VentaProductoPk ventaProductoId;
    @Column
    private int unidades;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    private Venta venta;

    public VentaProducto(){}

    public VentaProductoPk getVentaProductoId() {
        return ventaProductoId;
    }

    public void setVentaProductoId(VentaProductoPk ventaProductoId) {
        this.ventaProductoId = ventaProductoId;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
}
