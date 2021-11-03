package com.integrador4.service;

import com.integrador4.dto.RequestSaleDto;
import com.integrador4.dto.RequestSaleEntity;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.dto.SaleRequest;
import com.integrador4.entity.*;
import com.integrador4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    public ResponseEntity<Sale> save(SaleProductDto request) {

        Optional<Client> c = this.clientRepository.findById(request.getClient());
        if(c.isEmpty())  return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        Sale sale = new Sale(c.get(), Date.valueOf(LocalDate.now()));

        List<RequestSaleEntity> currentProducts = this.requestRepository.getPurchases(Date.valueOf(LocalDate.now()), c.get().getId());
        ArrayList<RequestSaleDto> requestSaleDtos = new ArrayList<>();
        currentProducts.forEach(
                currentProduct -> {
                    requestSaleDtos.add( new RequestSaleDto( currentProduct.getUnidades(), currentProduct.getIdProducto()) );
                });
        if (this.verifyQuantity(request.getProductQuantity(), requestSaleDtos)) {
            ArrayList<SaleProduct> saleProducts = this.addProducts(request.getProductQuantity(), sale);
            if (saleProducts != null) {
                sale = this.saleRepository.save(sale);
                Sale finalSale = sale;
                saleProducts.forEach(sp -> {
                    sp.setSaleID(finalSale.getIdSale());
                    sp.setSale(finalSale);
                });
                sale.getProductsSold().addAll(saleProducts);
                return new ResponseEntity<Sale>( this.saleRepository.save(sale), HttpStatus.OK);
            }
        }
        return null;
    }

    public Sale update(Integer id, SaleRequest saleRequest) {
        return this.saleRepository.save(saleRequest.toSale(id));
    }

    public List<Sale> findAll() {
        return this.saleRepository.findAll();
    }

    public Sale findById(Integer id ) {
        return this.saleRepository.getById(id);
    }

    public List<Sale> findByDate (Date date) {
        return this.saleRepository.findByDate(date);
    }

    public List<Sale> getReportByClientAndSales() {
        return this.saleRepository.reportClientAndSales();
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
            Optional<Product> p = productRepository.findById(rv.getIdProducto());
            if ( p.isEmpty() )
                return null;
            Product product = p.get();
            product.setStock( product.getStock() - rv.getUnidades() );
            products.add(product);
            amount += product.getPrice() * rv.getUnidades();
            saleProducts.add( new SaleProduct( rv.getUnidades(), product, sale) );
        }
        sale.setAmount( amount );
        this.productRepository.saveAll(products);
        return saleProducts;
    }
}
