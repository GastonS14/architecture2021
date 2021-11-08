package com.integrador4.service;

import com.integrador4.dto.*;
import com.integrador4.entity.*;
import com.integrador4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class SaleService {

    @Autowired private SaleRepository saleRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private RequestRepository requestRepository;
    @Autowired private BestSellProductRepository bestSellProductRepository;
    @Autowired private ClientSaleReportRepository clientSaleReportRepository;
    @Autowired private SalePerDayRepository salePerDayRepository;

    public SaleService() {}

    public Optional<Sale> save(RequestSale request) {
        Optional<Client> c = this.clientRepository.findById(request.getClient());
        if(c.isEmpty())  return Optional.empty();
        Sale sale = new Sale(c.get());
        ArrayList<SaleProductDto> saleProductDtos = request.getProductQuantity();
        boolean rightRequest = saleProductDtos
                .stream().anyMatch( i -> i.getUnidades() < 0 || i.getUnidades() > 3 ||
                    productRepository.findById(i.getIdProducto()).isEmpty() ||
                    productRepository.findById( i.getIdProducto()).get().getStock() < i.getUnidades()
                );
        if ( rightRequest ) return Optional.empty();
        List<SaleProductDto> currentProducts = this.requestRepository.getPurchases(Date.valueOf(LocalDate.now()), c.get().getId());
        List<SaleProductDto> alreadyBought = saleProductDtos.stream().filter(  currentProducts::contains ).collect(Collectors.toList());
        if (this.verifyQuantity( alreadyBought, currentProducts) ) {
            sale = this.saleRepository.save(sale);
            this.addProducts(saleProductDtos, sale);
            return Optional.of( this.saleRepository.save( sale ) );
        }
        return Optional.empty();
    }

    public List<Sale> findAll() {
        return this.saleRepository.findAll();
    }

    public Optional<Sale> findById(Integer id ) {
        return this.saleRepository.findById(id);
    }

    public Optional<List<SalePerDay>> findByDate (Date start, Date end ) {
        return this.salePerDayRepository.findByDate( start, end );
    }

    public List<ClientSaleReport> getReportByClientAndSales() {
        return this.clientSaleReportRepository.getClientSaleReport();
    }

    private boolean verifyQuantity (List<SaleProductDto> items, List<SaleProductDto> productsBought) {
        return items.stream().noneMatch(
                i -> i.getUnidades() + productsBought.get( productsBought.indexOf( i )).getUnidades() > 3 );
    }

    private void addProducts (ArrayList<SaleProductDto> items, Sale sale) {
        double amount = 0;
        for ( SaleProductDto rv : items ) {
            Product product = productRepository.findById(rv.getIdProducto()).get();
            product.setStock( product.getStock() - rv.getUnidades() );
            this.productRepository.save( product );
            amount += product.getPrice() * rv.getUnidades();
            sale.addProduct( new SaleProduct( rv.getUnidades(), product, sale ));
        }
        sale.setAmount( amount );
    }

    public Optional<BestSellProductDto> getBestSell() {
        return this.bestSellProductRepository.getBestSell();
    }

    public Optional<Sale> delete( Integer id ) {
        Optional<Sale> saleToDelete = this.saleRepository.findById( id );
        if ( saleToDelete.isEmpty() )
            return Optional.empty();
        this.saleRepository.delete( saleToDelete.get() );
        return saleToDelete;
    }
}
