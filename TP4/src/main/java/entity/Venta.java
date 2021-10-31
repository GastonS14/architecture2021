package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table( name = "venta")
public class Venta {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;
    @Column
    private LocalDate fecha;
    @ManyToOne
    private Cliente cliente;
    @Column
    private double monto;
    @Column
    private int unidades;
    @OneToMany ( mappedBy = "venta")
    private ArrayList<VentaProducto> productosVendidos;

    public long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public ArrayList<VentaProducto> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(ArrayList<VentaProducto> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }
}
