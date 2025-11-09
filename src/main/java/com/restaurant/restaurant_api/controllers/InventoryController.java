package com.restaurant.restaurant_api.controllers;

import com.restaurant.restaurant_api.dto.inventory.responses.InventoryItemDTO;
import com.restaurant.restaurant_api.dto.inventory.requests.CreateInventoryItemRequest;
import com.restaurant.restaurant_api.dto.inventory.requests.UpdateInventoryItemRequest;
import com.restaurant.restaurant_api.dto.inventory.requests.StockOperationRequest;
import com.restaurant.restaurant_api.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/items")
@CrossOrigin(origins = "http://localhost:4200")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CAMARERO', 'COCINERO')")
    public ResponseEntity<Page<InventoryItemDTO>> getAllItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long measurementUnitId,
            @RequestParam(required = false) Double minStock,
            @RequestParam(required = false) Double maxStock,
            Pageable pageable) {
        return ResponseEntity.ok(inventoryService.findAllItems(name, measurementUnitId, minStock, maxStock, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAMARERO', 'COCINERO')")
    public ResponseEntity<InventoryItemDTO> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.findById(id));
    }

    @GetMapping("/below-stock/{threshold}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAMARERO', 'COCINERO')")
    public ResponseEntity<List<InventoryItemDTO>> getItemsBelowStock(@PathVariable Double threshold) {
        return ResponseEntity.ok(inventoryService.findItemsBelowStock(threshold));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InventoryItemDTO> createItem(@Valid @RequestBody CreateInventoryItemRequest request) {
        return ResponseEntity.ok(inventoryService.createItem(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InventoryItemDTO> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInventoryItemRequest request) {
        return ResponseEntity.ok(inventoryService.updateItem(id, request));
    }

    @PatchMapping("/{id}/stock")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAMARERO')")
    public ResponseEntity<InventoryItemDTO> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody StockOperationRequest request) {
        return ResponseEntity.ok(inventoryService.updateStock(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recent")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAMARERO', 'COCINERO')")
    public ResponseEntity<List<InventoryItemDTO>> getRecentlyUpdated(
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(inventoryService.findRecentlyUpdated(limit));
    }
}
