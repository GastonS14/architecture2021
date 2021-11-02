package com.integrador4.controller;

import com.integrador4.dto.SaleProductDto;
import com.integrador4.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.SaleService;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAll () {
        return this.saleService.findAll();
    }

    @GetMapping(path = "/filter")
    public List<Sale> getAll (@RequestParam("date") Date fecha ) {
        return this.saleService.findByFecha( fecha );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById (@PathVariable("id") int id ) {
        return this.saleService.findById( id );
    }

    @GetMapping("/report")
    public List<Sale> report () {
        return this.saleService.getReport();
    }

    @PostMapping
    public ResponseEntity<Sale> newVenta (@RequestBody SaleProductDto vp ){
        return this.saleService.save( vp );
    }

    @PutMapping
    public Sale updateVenta (@RequestBody Sale sale) {
        return this.saleService.update(sale);
    }


}
