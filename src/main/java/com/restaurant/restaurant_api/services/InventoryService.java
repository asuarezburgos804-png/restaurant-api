package com.restaurant.restaurant_api.services;

import com.restaurant.restaurant_api.dto.inventory.responses.InventoryItemDTO;
import com.restaurant.restaurant_api.dto.inventory.responses.MeasurementUnitDTO;
import com.restaurant.restaurant_api.dto.inventory.requests.CreateInventoryItemRequest;
import com.restaurant.restaurant_api.dto.inventory.requests.UpdateInventoryItemRequest;
import com.restaurant.restaurant_api.dto.inventory.requests.StockOperationRequest;
import com.restaurant.restaurant_api.models.InventoryItem;
import com.restaurant.restaurant_api.models.MeasurementUnit;
import com.restaurant.restaurant_api.repositories.InventoryItemRepository;
import com.restaurant.restaurant_api.repositories.MeasurementUnitRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final InventoryItemRepository inventoryItemRepository;
    private final MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    public InventoryService(InventoryItemRepository inventoryItemRepository,
            MeasurementUnitRepository measurementUnitRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.measurementUnitRepository = measurementUnitRepository;
    }

    @Transactional
    public Page<InventoryItemDTO> findAllItems(String name, Long measurementUnitId, Double minStock, Double maxStock,
            Pageable pageable) {
        return inventoryItemRepository.findByFilters(name, measurementUnitId, minStock, maxStock, pageable)
                .map(this::convertToDTO);
    }

    @Transactional
    public InventoryItemDTO findById(Long id) {
        return inventoryItemRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    @Transactional
    public List<InventoryItemDTO> findItemsBelowStock(Double threshold) {
        return inventoryItemRepository.findByStockLessThan(threshold)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public InventoryItemDTO createItem(CreateInventoryItemRequest request) {
        if (inventoryItemRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("An item with this name already exists");
        }

        InventoryItem item = InventoryItem.builder()
                .name(request.getName())
                .unitPrice(request.getUnitPrice())
                .stock(request.getStock())
                .build();

        if (request.getMeasurementUnitId() != null) {
            MeasurementUnit unit = measurementUnitRepository.findById(request.getMeasurementUnitId())
                    .orElseThrow(() -> new EntityNotFoundException("Measurement unit not found"));
            item.setMeasurementUnit(unit);
        }

        return convertToDTO(inventoryItemRepository.save(item));
    }

    @Transactional
    public InventoryItemDTO updateItem(Long id, UpdateInventoryItemRequest request) {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        if (request.getName() != null) {
            if (!item.getName().equalsIgnoreCase(request.getName()) &&
                    inventoryItemRepository.existsByNameIgnoreCase(request.getName())) {
                throw new IllegalArgumentException("An item with this name already exists");
            }
            item.setName(request.getName());
        }

        if (request.getUnitPrice() != null) {
            item.setUnitPrice(request.getUnitPrice());
        }

        if (request.getMeasurementUnitId() != null) {
            MeasurementUnit unit = measurementUnitRepository.findById(request.getMeasurementUnitId())
                    .orElseThrow(() -> new EntityNotFoundException("Measurement unit not found"));
            item.setMeasurementUnit(unit);
        }

        return convertToDTO(inventoryItemRepository.save(item));
    }

    @Transactional
    public InventoryItemDTO updateStock(Long id, StockOperationRequest request) {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        double newStock;
        if ("increase".equals(request.getOperation())) {
            newStock = item.getStock() + request.getAmount();
        } else if ("decrease".equals(request.getOperation())) {
            newStock = item.getStock() - request.getAmount();
            if (newStock < 0) {
                throw new IllegalArgumentException("Stock cannot be negative");
            }
        } else {
            throw new IllegalArgumentException("Invalid operation: " + request.getOperation());
        }

        item.setStock(newStock);
        return convertToDTO(inventoryItemRepository.save(item));
    }

    @Transactional
    public void deleteItem(Long id) {
        if (!inventoryItemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item not found");
        }
        inventoryItemRepository.deleteById(id);
    }

    @Transactional
    public List<InventoryItemDTO> findRecentlyUpdated(int limit) {
        return inventoryItemRepository.findRecentlyUpdatedItems(Pageable.ofSize(limit))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private InventoryItemDTO convertToDTO(InventoryItem item) {
        return InventoryItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .unitPrice(item.getUnitPrice())
                .stock(item.getStock())
                .measurementUnit(item.getMeasurementUnit() != null ? MeasurementUnitDTO.builder()
                        .id(item.getMeasurementUnit().getId())
                        .name(item.getMeasurementUnit().getName())
                        .symbol(item.getMeasurementUnit().getSymbol())
                        .build() : null)
                .totalPrice(item.getTotalPrice())
                .lastUpdated(item.getLastUpdated())
                .createdAt(item.getCreatedAt())
                .build();
    }
}
