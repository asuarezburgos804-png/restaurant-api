package com.restaurant.restaurant_api.services;

import com.restaurant.restaurant_api.dto.sale.requests.CreateSaleRequest;
import com.restaurant.restaurant_api.dto.sale.requests.UpdateSaleRequest;
import com.restaurant.restaurant_api.dto.sale.responses.SaleDTO;
import com.restaurant.restaurant_api.dto.sale.responses.SaleItemDTO;
import com.restaurant.restaurant_api.dto.menu.responses.MenuItemDTO;
import com.restaurant.restaurant_api.models.Sale;
import com.restaurant.restaurant_api.models.SaleItem;
import com.restaurant.restaurant_api.models.MenuItem;
import com.restaurant.restaurant_api.repositories.SaleRepository;
import com.restaurant.restaurant_api.repositories.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private static final Logger log = LoggerFactory.getLogger(SaleService.class);
    private final SaleRepository saleRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, MenuItemRepository menuItemRepository) {
        this.saleRepository = saleRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Transactional
    public Page<SaleDTO> getAllSales(String name, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return saleRepository.findByFilters(name, startDate, endDate, pageable)
                .map(this::convertToDTO);
    }

    @Transactional
    public SaleDTO getSaleById(Long id) {
        return saleRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));
    }

    @Transactional
    public SaleDTO createSale(CreateSaleRequest request) {
        log.info("Creating sale with request: {}", request);
        log.info("Item IDs received: {}", request.getItemIds());

        // Agrupar los IDs por frecuencia
        Map<Long, Long> itemFrequency = request.getItemIds().stream()
                .collect(Collectors.groupingBy(itemId -> itemId, Collectors.counting()));

        // Obtener los IDs únicos
        Set<Long> uniqueIds = new HashSet<>(request.getItemIds());
        List<MenuItem> menuItems = menuItemRepository.findAllById(uniqueIds);
        log.info("Found menu items: {}", menuItems.stream().map(MenuItem::getId).collect(Collectors.toList()));

        if (menuItems.size() != uniqueIds.size()) {
            List<Long> foundIds = menuItems.stream().map(MenuItem::getId).collect(Collectors.toList());
            List<Long> missingIds = uniqueIds.stream()
                    .filter(itemId -> !foundIds.contains(itemId))
                    .collect(Collectors.toList());
            log.error("Missing menu items with IDs: {}", missingIds);
            throw new EntityNotFoundException("Uno o más items no fueron encontrados: " + missingIds);
        }

        Sale sale = Sale.builder()
                .name(request.getName())
                .description(request.getDescription())
                .saleDate(LocalDateTime.now())
                .totalPrice(java.math.BigDecimal.ZERO)
                .build();

        // Crear SaleItems basados en la frecuencia
        List<SaleItem> saleItems = menuItems.stream()
                .map(menuItem -> {
                    int quantity = itemFrequency.get(menuItem.getId()).intValue();
                    List<SaleItem> items = new ArrayList<>();
                    for (int i = 0; i < quantity; i++) {
                        items.add(SaleItem.builder()
                                .menuItem(menuItem)
                                .priceAtTime(menuItem.getPrice())
                                .build());
                    }
                    return items;
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());

        saleItems.forEach(sale::addItem);
        sale.calculateTotalPrice();

        Sale savedSale = saleRepository.save(sale);
        log.info("Sale created successfully with ID: {}", savedSale.getId());
        return convertToDTO(savedSale);
    }

    @Transactional
    public SaleDTO updateSale(Long id, UpdateSaleRequest request) {
        log.info("Updating sale with ID: {} and request: {}", id, request);
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));

        if (request.getName() != null) {
            sale.setName(request.getName());
        }

        if (request.getDescription() != null) {
            sale.setDescription(request.getDescription());
        }

        if (request.getItemIds() != null) {
            log.info("Updating items for sale ID: {}, new item IDs: {}", id, request.getItemIds());

            // Agrupar los IDs por frecuencia
            Map<Long, Long> itemFrequency = request.getItemIds().stream()
                    .collect(Collectors.groupingBy(itemId -> itemId, Collectors.counting()));

            // Obtener los IDs únicos
            Set<Long> uniqueIds = new HashSet<>(request.getItemIds());
            List<MenuItem> menuItems = menuItemRepository.findAllById(uniqueIds);

            if (menuItems.size() != uniqueIds.size()) {
                List<Long> foundIds = menuItems.stream().map(MenuItem::getId).collect(Collectors.toList());
                List<Long> missingIds = uniqueIds.stream()
                        .filter(itemId -> !foundIds.contains(itemId))
                        .collect(Collectors.toList());
                log.error("Missing menu items with IDs: {}", missingIds);
                throw new EntityNotFoundException("Uno o más items no fueron encontrados: " + missingIds);
            }

            sale.clearItems();

            // Crear SaleItems basados en la frecuencia
            List<SaleItem> saleItems = menuItems.stream()
                    .map(menuItem -> {
                        int quantity = itemFrequency.get(menuItem.getId()).intValue();
                        List<SaleItem> items = new ArrayList<>();
                        for (int i = 0; i < quantity; i++) {
                            items.add(SaleItem.builder()
                                    .menuItem(menuItem)
                                    .priceAtTime(menuItem.getPrice())
                                    .build());
                        }
                        return items;
                    })
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            saleItems.forEach(sale::addItem);
            sale.calculateTotalPrice();
        }

        Sale updatedSale = saleRepository.save(sale);
        log.info("Sale updated successfully with ID: {}", updatedSale.getId());
        return convertToDTO(updatedSale);
    }

    @Transactional
    public void deleteSale(Long id) {
        log.info("Deleting sale with ID: {}", id);
        if (!saleRepository.existsById(id)) {
            throw new EntityNotFoundException("Venta no encontrada");
        }
        saleRepository.deleteById(id);
        log.info("Sale deleted successfully with ID: {}", id);
    }

    @Transactional
    public List<SaleDTO> getRecentSales(int limit) {
        return saleRepository.findRecentSales(Pageable.ofSize(limit))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SaleDTO convertToDTO(Sale sale) {
        return SaleDTO.builder()
                .id(sale.getId())
                .name(sale.getName())
                .description(sale.getDescription())
                .saleDate(sale.getSaleDate())
                .totalPrice(sale.getTotalPrice())
                .items(sale.getItems().stream()
                        .map(this::convertToSaleItemDTO)
                        .collect(Collectors.toList()))
                .createdAt(sale.getCreatedAt())
                .updatedAt(sale.getUpdatedAt())
                .build();
    }

    private SaleItemDTO convertToSaleItemDTO(SaleItem saleItem) {
        return SaleItemDTO.builder()
                .id(saleItem.getId())
                .menuItem(convertToMenuItemDTO(saleItem.getMenuItem()))
                .priceAtTime(saleItem.getPriceAtTime())
                .createdAt(saleItem.getCreatedAt())
                .build();
    }

    private MenuItemDTO convertToMenuItemDTO(MenuItem menuItem) {
        return MenuItemDTO.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .imagePath(menuItem.getImagePath())
                .available(menuItem.isAvailable())
                .build();
    }
}
