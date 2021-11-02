package com.integrador4.service;

import com.integrador4.dto.RequestVenta;
import com.integrador4.dto.SaleRequest;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.entity.*;
import com.integrador4.repository.ClientRepository;
import com.integrador4.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ProductRepository;
import com.integrador4.repository.SaleRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class SaleService {

    @Autowired private SaleRepository saleRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private RequestRepository requestRepository;

    public SaleService() {}

    public Sale save(SaleProductDto request) {

        Optional<Client> c = this.clientRepository.findById(request.getClient());
        if(c.isEmpty()) return null;
        Sale sale = new Sale(c.get(), Date.valueOf(LocalDate.now()));

        List<RequestVentaEntidad> currentProducts = this.requestRepository.getPurchases(Date.valueOf(LocalDate.now()), c.get().getId());
        sale = this.saleRepository.save(sale);
        if ( this.verifyQuantity( request.getProductosCantidad(), currentProducts.subList(0, currentProducts.size()))) {
            if (this.addProducts(request.getProductosCantidad(), sale) ) {
                this.saleRepository.save(sale);
                return this.saleRepository.getById( sale.getIdSale() );
            }
        }
        return null;
    }

    /*
    public ResponseEntity<Sale> save2 (SaleProductDto request ) {

        Optional<Client> c = this.clientRepository.findById( request.getCliente() );
        if ( c.isEmpty() ) return new ResponseEntity<>( HttpStatus.BAD_REQUEST);

        List<RequestVentaEntidad> currentProducts = this.requestRepo.getPurchases( Date.valueOf( LocalDate.now() ), c.get().getId() );
        ArrayList<RequestSale> requestSaleDtos = new ArrayList<>();
        currentProducts.forEach(
                currentProduct -> {
                    requestSaleDtos.add( new RequestSaleDto( currentProduct.getUnidades(), currentProduct.getIdProducto()) );
                });
        if ( this.verifyQuantity( request.getProductosCantidad(), requestSaleDtos ) ) {
            ArrayList<SaleProduct> saleProducts = this.addProducts(request.getProductosCantidad(), sale);
            if ( saleProducts != null ) {
                sale = this.saleRepo.save(sale);
                Sale finalSale = sale;
                saleProducts.forEach(sp -> {
                    sp.setSaleID(finalSale.getId_sale());
                    sp.setSale(finalSale);
                });
                sale.getProductsSold().addAll(saleProducts);
                return new ResponseEntity<Sale>( this.saleRepo.save(sale), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

     */

    public Sale update(Integer id, SaleRequest saleRequest) {
        return this.saleRepository.save(saleRequest.toSale(id));
    }

    public List<Sale> findAll() {
        return this.saleRepository.findAll();
    }

    public Sale findById(long id ) {
        return this.saleRepository.getById( id );
    }

    public List<Sale> findByFecha (Date fecha ) {
        return this.saleRepository.getAllByFecha( fecha );
    }

    public List<Sale> getReport() {
        return this.saleRepository.reportClienteVentas();
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

    private boolean addProducts ( ArrayList<RequestVenta> items, Sale sale) {
        double amount = 0;
        ArrayList<Product> products = new ArrayList<>();
        System.out.println( "linea 89");
        for ( RequestVenta rv : items ) {
            Optional<Product> p = productRepository.findById(rv.getIdProducto());
            if ( ! p.isPresent() ) {
                return false;
            }
            Product product = p.get();
            product.setStock( product.getStock() - rv.getUnidades() );
            products.add(product);
            amount += product.getPrice() * rv.getUnidades();
            sale.addProduct( new SaleProduct(
                    rv.getUnidades(), product, sale) );
        }
        sale.setAmount( amount );
        this.updateProductsStock( products );
        return true;
    }

    private void updateProductsStock ( ArrayList<Product> products ) {
        products.forEach( p -> this.productRepository.save( p ) );
    }
}
