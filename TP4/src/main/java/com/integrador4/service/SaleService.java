package com.integrador4.service;

import com.integrador4.dto.BestSellProductDto;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.dto.RequestSale;
import com.integrador4.dto.SaleRequest;
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

    public Sale update(Integer id, SaleRequest saleRequest) {
        return this.saleRepository.save(saleRequest.toSale(id));
    }

    public List<Sale> findAll() {
        return this.saleRepository.findAll();
    }

    public Optional<Sale> findById(Integer id ) {
        return this.saleRepository.findById(id);
    }

    public List<Sale> findByDate (Date date) {
        return this.saleRepository.findByDate(date);
    }

    public List<Sale> getReportByClientAndSales() {
        return this.saleRepository.reportClientAndSales();
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
}
