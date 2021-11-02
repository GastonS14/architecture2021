package com.integrador4.service;

import com.integrador4.dto.RequestSaleDto;
import com.integrador4.dto.RequestSaleEntity;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.entity.*;
import com.integrador4.repository.ClientRepo;
import com.integrador4.repository.RequestSaleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.integrador4.repository.ProductRepo;
import com.integrador4.repository.SaleRepo;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public final class SaleService {
    @Autowired private SaleRepo saleRepo;
    @Autowired private ProductRepo productRepo;
    @Autowired private ClientRepo clientRepo;
    @Autowired private RequestSaleRepo requestSaleRepo;

    public SaleService() {}

    public ResponseEntity<Sale> save (SaleProductDto request ) {
        Sale sale = new Sale();
        Optional<Client> c = this.clientRepo.findById( request.getCliente() );
        if ( c.isEmpty() )
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        sale.setClient( c.get() );
        sale.setDate( Date.valueOf( LocalDate.now() ) );
        List<RequestSaleEntity> currentProducts = this.requestSaleRepo.getpurchases( Date.valueOf( LocalDate.now() ), c.get().getId() );
        ArrayList<RequestSaleDto> requestSaleDtos = new ArrayList<>();
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

    public Sale update (Sale sale) {
        return this.saleRepo.save(sale);
    }

    public List<Sale> findAll () {
        return this.saleRepo.findAll();
    }

    public ResponseEntity<Sale> findById (int id ) {
        Optional<Sale> sale = this.saleRepo.findById(id);
        if ( sale.isEmpty() )
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        return new ResponseEntity( sale, HttpStatus.OK);
    }

    public List<Sale> findByFecha (Date fecha ) {
        return this.saleRepo.getAllByFecha( fecha );
    }

    public List<Sale> getReport() {
        return this.saleRepo.reportClienteVentas();
    }

    private boolean verifyQuantity (ArrayList<RequestSaleDto> items, List<RequestSaleDto> productsBought) {
        for ( RequestSaleDto item : items ) {
            int index = productsBought.indexOf( item );
            if ( index > 0 ) {
                RequestSaleDto rv = productsBought.get( index );
                if ( ( item.getUnidades() + rv.getUnidades() ) > 3 )
                    return false;
            }
        }
        return true;
    }

    private ArrayList<SaleProduct> addProducts (ArrayList<RequestSaleDto> items, Sale sale) {
        double amount = 0; ArrayList<Product> products = new ArrayList<>();
        ArrayList<SaleProduct> saleProducts = new ArrayList<>();
        for ( RequestSaleDto rv : items ) {
            Optional<Product> p = productRepo.findById(rv.getIdProducto());
            if ( p.isEmpty() )
                return null;
            Product product = p.get();
            product.setStock( product.getStock() - rv.getUnidades() );
            products.add(product);
            amount += product.getPrice() * rv.getUnidades();
            saleProducts.add( new SaleProduct( rv.getUnidades(), product, sale) );
        }
        sale.setAmount( amount );
        this.productRepo.saveAll(products);
        return saleProducts;
    }

}
