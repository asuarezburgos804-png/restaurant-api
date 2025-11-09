package com.restaurant.restaurant_api.controllers;

import com.restaurant.restaurant_api.dto.sale.requests.CreateSaleRequest;
import com.restaurant.restaurant_api.dto.sale.requests.UpdateSaleRequest;
import com.restaurant.restaurant_api.dto.sale.responses.SaleDTO;
import com.restaurant.restaurant_api.services.SaleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:49378" })
public class SaleController {
    private static final Logger log = LoggerFactory.getLogger(SaleController.class);
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<SaleDTO>> getAllSales(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Pageable pageable) {
        log.info("Getting all sales with filters - name: {}, startDate: {}, endDate: {}", name, startDate, endDate);
        return ResponseEntity.ok(saleService.getAllSales(name, startDate, endDate, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaleDTO> getSale(@PathVariable Long id) {
        log.info("Getting sale with ID: {}", id);
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody CreateSaleRequest request) {
        log.info("Creating new sale: {}", request);
        try {
            SaleDTO createdSale = saleService.createSale(request);
            log.info("Sale created successfully with ID: {}", createdSale.getId());
            return ResponseEntity.ok(createdSale);
        } catch (Exception e) {
            log.error("Error creating sale: {}", e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaleDTO> updateSale(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSaleRequest request) {
        log.info("Updating sale with ID: {} - Request: {}", id, request);
        try {
            SaleDTO updatedSale = saleService.updateSale(id, request);
            log.info("Sale updated successfully with ID: {}", updatedSale.getId());
            return ResponseEntity.ok(updatedSale);
        } catch (Exception e) {
            log.error("Error updating sale: {}", e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        log.info("Deleting sale with ID: {}", id);
        try {
            saleService.deleteSale(id);
            log.info("Sale deleted successfully with ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting sale: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/recent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SaleDTO>> getRecentSales(
            @RequestParam(defaultValue = "5") int limit) {
        log.info("Getting recent sales with limit: {}", limit);
        return ResponseEntity.ok(saleService.getRecentSales(limit));
    }
}
