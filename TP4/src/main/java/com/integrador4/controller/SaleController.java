package com.integrador4.controller;

import com.integrador4.dto.SaleRequest;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.entity.Sale;
import com.integrador4.extensions.ObjectExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.SaleService;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAll(HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        return this.saleService.findAll();
    }

    /**
     * @param date to filter by day
     * @return all the sales by day
     */
    @GetMapping("/filter")
    public List<Sale> getAll(@RequestParam Date date, HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        return this.saleService.findByDate(date);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable Integer id, HttpServletRequest request ) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        Optional<Sale> sale = Optional.of(this.saleService.findById(id));
        if (sale.isEmpty())
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        return new ResponseEntity(sale, HttpStatus.OK);
    }

    /**
     * @return sales by day and client
     */
    @GetMapping("/report")
    public List<Sale> report() {
        return this.saleService.getReportByClientAndSales();
    }

    @PostMapping
    public ResponseEntity<Sale> save(@RequestBody SaleProductDto body, HttpServletRequest request){
        logger.info(
            "method={} uri={} body={}",
            request.getMethod(), request.getRequestURI(), ObjectExtension.toJson(body)
        );
        return new ResponseEntity(this.saleService.save(body), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Sale update(
        @RequestBody SaleRequest body,
        @PathVariable Integer id,
        HttpServletRequest request) {
        logger.info(
            "method={} uri={} body={}",
            request.getMethod(), request.getRequestURI(), ObjectExtension.toJson(body)
        );
        return this.saleService.update(id, body);
    }


}
