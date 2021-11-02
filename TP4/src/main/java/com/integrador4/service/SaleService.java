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
        if (this.verifyQuantity(request.getProductQuantity(), currentProducts.subList(0, currentProducts.size()))) {
            if (this.addProducts(request.getProductQuantity(), sale) ) {
                this.saleRepository.save(sale);
                return this.saleRepository.getById( sale.getIdSale() );
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

    public Sale findById(long id ) {
        return this.saleRepository.getById( id );
    }

    public List<Sale> findByDate (Date date) {
        return this.saleRepository.findByDate(date);
    }

    public List<Sale> getReportByClientAndSales() {
        return this.saleRepository.reportClientAndSales();
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

    private boolean addProducts(ArrayList<RequestVenta> items, Sale sale) {
        double amount = 0;
        ArrayList<Product> products = new ArrayList<>();
        for ( RequestVenta rv : items ) {
            Optional<Product> p = productRepository.findById(rv.getIdProducto());
            if (p.isEmpty()) {
                return false;
            }
            Product product = p.get();
            product.setStock( product.getStock() - rv.getUnidades() );
            products.add(product);
            amount += product.getPrice() * rv.getUnidades();
            sale.addProduct( new SaleProduct(rv.getUnidades(), product, sale) );
        }
        sale.setAmount(amount);
        this.updateProductsStock(products);
        return true;
    }

    private void updateProductsStock ( ArrayList<Product> products ) {
        this.productRepository.saveAll(products);
    }
}
