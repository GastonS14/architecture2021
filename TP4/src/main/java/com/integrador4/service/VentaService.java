package com.integrador4.service;

import com.integrador4.dto.RequestVenta;
import com.integrador4.dto.VentaProductoDto;
import com.integrador4.entity.*;
import com.integrador4.repository.ClienteRepo;
import com.integrador4.repository.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ProductoRepo;
import com.integrador4.repository.VentaRepo;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public final class VentaService {
    @Autowired private VentaRepo ventaRepo;
    @Autowired private ProductoRepo productoRepo;
    @Autowired private ClienteRepo clienteRepo;
    @Autowired private RequestRepo requestRepo;

    public VentaService() {}

    public Venta save ( VentaProductoDto request ) {
        Venta venta = new Venta ();
        Optional<Cliente> c = this.clienteRepo.findById( request.getCliente() );
        if ( !c.isPresent() )
            return null;
        venta.setClient( c.get() );
        venta.setDate( Date.valueOf( LocalDate.now() ) );
        List<RequestVentaEntidad> currentProducts = this.requestRepo.getpurchases( Date.valueOf( LocalDate.now() ), c.get().getId() );
        venta = this.ventaRepo.save( venta );
        if ( this.verifyQuantity( request.getProductosCantidad(), currentProducts.subList(0, currentProducts.size()) ) ) {
            if (this.addProducts(request.getProductosCantidad(), venta) ) {
                System.out.println( venta);
                this.ventaRepo.save(venta);
                return this.ventaRepo.getById( venta.getId_sale() );
            }
        }
        return null;
    }

    public Venta update ( Venta venta ) {
        return this.ventaRepo.save( venta );
    }

    public List<Venta> findAll () {
        return this.ventaRepo.findAll();
    }

    public Venta findById ( long id ) {
        return this.ventaRepo.getById( id );
    }

    public List<Venta> findByFecha ( Date fecha ) {
        return this.ventaRepo.getAllByFecha( fecha );
    }

    public List<Venta> getReport() {
        return this.ventaRepo.reportClienteVentas();
    }

    private boolean verifyQuantity ( ArrayList<RequestVenta> items, List<RequestVentaEntidad> productsBought) {
        for ( RequestVenta item : items ) {
            int index = productsBought.indexOf( item );
            if ( index > 0 ) {
                RequestVentaEntidad rv = productsBought.get( index );
                if ( ( item.getUnidades() + rv.getUnidades() ) > 2 ) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean addProducts ( ArrayList<RequestVenta> items, Venta venta ) {
        double amount = 0;
        ArrayList<Producto> products = new ArrayList<>();
        System.out.println( "linea 89");
        for ( RequestVenta rv : items ) {
            Optional<Producto> p = productoRepo.findById(rv.getIdProducto());
            if ( ! p.isPresent() ) {
                return false;
            }
            Producto producto = p.get();
            producto.setStock( producto.getStock() - rv.getUnidades() );
            products.add( producto );
            amount += producto.getPrice() * rv.getUnidades();
            venta.addProduct( new VentaProducto(
                    rv.getUnidades(), producto, venta ) );
        }
        venta.setAmount( amount );
        this.updateProductsStock( products );
        return true;
    }

    private void updateProductsStock ( ArrayList<Producto> products ) {
        products.forEach( p -> this.productoRepo.save( p ) );
    }
}
