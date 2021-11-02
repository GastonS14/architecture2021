package com.integrador4.controller;

import com.integrador4.dto.VentaProductoDto;
import com.integrador4.entity.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.VentaService;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
    @Autowired
    private VentaService saleService;

    @GetMapping
    public List<Venta> getAll () {
        return this.saleService.findAll();
    }

    @GetMapping(path = "/filter")
    public List<Venta> getAll (@RequestParam("date") Date fecha ) {
        return this.saleService.findByFecha( fecha );
    }

    @GetMapping("/{id}")
    public Venta getById ( @PathVariable("id") long id ) {
        return this.saleService.findById( id );
    }

    @GetMapping("/report")
    public List<Venta> report () {
        return this.saleService.getReport();
    }

    @PostMapping
    public Venta newVenta (@RequestBody VentaProductoDto vp ){
        return this.saleService.save( vp );
    }

    @PutMapping
    public Venta updateVenta ( @RequestBody Venta venta ) {
        return this.saleService.update( venta );
    }


}
