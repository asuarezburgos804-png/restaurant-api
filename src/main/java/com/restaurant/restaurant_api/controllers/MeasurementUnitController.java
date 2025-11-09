package com.restaurant.restaurant_api.controllers;

import com.restaurant.restaurant_api.dto.inventory.responses.MeasurementUnitDTO;
import com.restaurant.restaurant_api.services.MeasurementUnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/units")
@CrossOrigin(origins = "http://localhost:4200")
public class MeasurementUnitController {
    private final MeasurementUnitService measurementUnitService;

    @Autowired
    public MeasurementUnitController(MeasurementUnitService measurementUnitService) {
        this.measurementUnitService = measurementUnitService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CAMARERO', 'COCINERO')")
    public ResponseEntity<List<MeasurementUnitDTO>> getAllUnits() {
        return ResponseEntity.ok(measurementUnitService.getAllUnits());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAMARERO', 'COCINERO')")
    public ResponseEntity<MeasurementUnitDTO> getUnitById(@PathVariable Long id) {
        return ResponseEntity.ok(measurementUnitService.getUnitById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MeasurementUnitDTO> createUnit(@Valid @RequestBody MeasurementUnitDTO request) {
        return ResponseEntity.ok(measurementUnitService.createUnit(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MeasurementUnitDTO> updateUnit(
            @PathVariable Long id,
            @Valid @RequestBody MeasurementUnitDTO request) {
        return ResponseEntity.ok(measurementUnitService.updateUnit(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        measurementUnitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }
}
