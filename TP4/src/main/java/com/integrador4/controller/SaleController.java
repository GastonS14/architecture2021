package com.integrador4.controller;

import com.integrador4.dto.SaleRequest;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.entity.Sale;
import com.integrador4.extensions.ObjectExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.SaleService;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAll(HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getPathInfo());
        return this.saleService.findAll();
    }

    @GetMapping("/filter")
    public List<Sale> getAll(@RequestParam("date") Date fecha, HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getPathInfo());
        return this.saleService.findByFecha( fecha );
    }

    @GetMapping("/{id}")
    public Sale getById(@PathVariable("id") long id, HttpServletRequest request ) {
        logger.info("method={} uri={}", request.getMethod(), request.getPathInfo());
        return this.saleService.findById(id);
    }

    @GetMapping("/report")
    public List<Sale> report() {
        return this.saleService.getReport();
    }

    @PostMapping
    public Sale save(@RequestBody SaleProductDto body, HttpServletRequest request){
        logger.info(
            "method={} uri={} body={}",
            request.getMethod(), request.getPathInfo(), ObjectExtension.toJson(body)
        );
        return this.saleService.save( body );
    }

    @PutMapping("/{id}")
    public Sale update(
        @RequestBody SaleRequest body,
        @PathVariable Integer id,
        HttpServletRequest request) {
        logger.info(
            "method={} uri={} body={}",
            request.getMethod(), request.getPathInfo(), ObjectExtension.toJson(body)
        );
        return this.saleService.update(id, body);
    }


}
