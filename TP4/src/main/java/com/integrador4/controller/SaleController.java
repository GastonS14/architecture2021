package com.integrador4.controller;

import com.integrador4.dto.ClientSaleReport;
import com.integrador4.dto.RequestSale;
import com.integrador4.dto.SalePerDay;
import com.integrador4.entity.Sale;
import com.integrador4.extensions.ObjectExtension;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.integrador4.service.SaleService;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleService saleService;

    @GetMapping
    @Operation( summary = "Get all sales", description = "Get list of sales",
            responses = @ApiResponse(description = "List of sales", responseCode = "200",content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Sale.class)))))
    public List<Sale> getAll(HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        return this.saleService.findAll();
    }

    /**
     * @param start initial date
     * @param end end
     * @return sales that are between both dates
     */
    @GetMapping("/inRange")
    @Operation( summary = "Filter sales", description = "Get sales filter by dates",
        responses = @ApiResponse(description = "List of sales", responseCode = "200",content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = SalePerDay.class)))))
    public List<SalePerDay> getReportPerDay(@RequestParam(name = "startDate") Date start,
                                   @RequestParam(name = "endDate") Date end, HttpServletRequest request) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        Optional<List<SalePerDay>> sales = this.saleService.findByDate( start, end );
        if ( sales.isEmpty() )
            return new ArrayList<>();
        return sales.get();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Get sale", description = "Get sale by ID",
    responses = { @ApiResponse(description = "The sale", responseCode = "200",content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Sale.class))),
                  @ApiResponse( responseCode = "404", description = "Sale not found")})
    public ResponseEntity<Sale> getById(@PathVariable Integer id, HttpServletRequest request ) {
        logger.info("method={} uri={}", request.getMethod(), request.getRequestURI());
        Optional<Sale> sale = this.saleService.findById(id);
        if (sale.isEmpty())
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        return new ResponseEntity(sale, HttpStatus.OK);
    }

    /**
     * @return sales by client
     */
    @GetMapping("/report")
    @Operation( summary = "Get report", description = "Get report of sales for each client",
    responses = @ApiResponse(description = "The report",responseCode="200", content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ClientSaleReport.class)))))
    public List<ClientSaleReport> report() {
        return this.saleService.getReportByClientAndSales();
    }

    @PostMapping
    @Operation( summary = "Add sale", description = "Add new sale",
    responses = { @ApiResponse(description = "The new sale",responseCode="201", content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Sale.class))),
                @ApiResponse( description = "Bad request", responseCode = "400") })
    public ResponseEntity<Sale> save(@RequestBody RequestSale body, HttpServletRequest request){
        logger.info(
            "method={} uri={} body={}",
            request.getMethod(), request.getRequestURI(), ObjectExtension.toJson(body)
        );
        Optional<Sale> sale = this.saleService.save( body );
        if ( sale.isEmpty() )
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        return new ResponseEntity( sale.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation( summary = "Remove sale", description = "Remove sale by id",
            responses = { @ApiResponse(description = "The sale removed",responseCode="20", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Sale.class))),
                    @ApiResponse( description = "Sale not found", responseCode = "404") })
    public ResponseEntity delete ( @Parameter(description= "the id that needs to be deleted", required = true)
                                       @PathVariable Integer id ) {
        Optional<Sale> sale = this.saleService.delete( id );
        if ( sale.isEmpty() )
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        return new ResponseEntity( sale, HttpStatus.OK);
    }


}
