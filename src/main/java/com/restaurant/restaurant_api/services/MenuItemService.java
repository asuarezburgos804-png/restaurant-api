package com.restaurant.restaurant_api.services;

import com.restaurant.restaurant_api.dto.menu.requests.CreateMenuItemRequest;
import com.restaurant.restaurant_api.dto.menu.requests.UpdateMenuItemRequest;
import com.restaurant.restaurant_api.dto.menu.responses.MenuCategoryDTO;
import com.restaurant.restaurant_api.dto.menu.responses.MenuItemDTO;
import com.restaurant.restaurant_api.models.MenuItem;
import com.restaurant.restaurant_api.models.MenuItemCategory;
import com.restaurant.restaurant_api.repositories.MenuCategoryRepository;
import com.restaurant.restaurant_api.repositories.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MenuCategoryRepository categoryRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository, MenuCategoryRepository categoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Page<MenuItemDTO> getAllItems(String name, Long categoryId, Boolean available, Pageable pageable) {
        return menuItemRepository.findByFilters(name, categoryId, available, pageable)
                .map(this::convertToDTO);
    }

    @Transactional
    public MenuItemDTO getItemById(Long id) {
        return menuItemRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Item no encontrado"));
    }

    @Transactional
    public List<MenuItemDTO> getItemsByCategory(Long categoryId) {
        return menuItemRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MenuItemDTO> getAvailableItems() {
        return menuItemRepository.findByAvailableTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public MenuItemDTO createItem(CreateMenuItemRequest request) {
        try {
            if (menuItemRepository.existsByNameIgnoreCase(request.getName())) {
                throw new IllegalArgumentException("Ya existe un item con este nombre");
            }

            MenuItemCategory category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

            MenuItem item = MenuItem.builder()
                    .name(request.getName())
                    .price(request.getPrice())
                    .description(request.getDescription())
                    .category(category)
                    .imagePath(request.getImagePath())
                    .available(request.isAvailable())
                    .build();

            System.out.println("Creando item de menú: " + item);
            MenuItem savedItem = menuItemRepository.save(item);
            System.out.println("Item de menú creado: " + savedItem);

            return convertToDTO(savedItem);
        } catch (Exception e) {
            System.err.println("Error al crear item de menú: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public MenuItemDTO updateItem(Long id, UpdateMenuItemRequest request) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item no encontrado"));

        if (request.getName() != null) {
            if (!item.getName().equalsIgnoreCase(request.getName()) &&
                    menuItemRepository.existsByNameIgnoreCase(request.getName())) {
                throw new IllegalArgumentException("Ya existe un item con este nombre");
            }
            item.setName(request.getName());
        }

        if (request.getPrice() != null) {
            item.setPrice(request.getPrice());
        }

        if (request.getDescription() != null) {
            item.setDescription(request.getDescription());
        }

        if (request.getCategoryId() != null) {
            MenuItemCategory category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
            item.setCategory(category);
        }

        if (request.getImagePath() != null) {
            item.setImagePath(request.getImagePath());
        }

        if (request.getAvailable() != null) {
            item.setAvailable(request.getAvailable());
        }

        return convertToDTO(menuItemRepository.save(item));
    }

    @Transactional
    public void deleteItem(Long id) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item no encontrado"));

        menuItemRepository.deleteById(id);
    }

    @Transactional
    public List<MenuItemDTO> getRecentlyUpdated(int limit) {
        return menuItemRepository.findRecentlyUpdated(Pageable.ofSize(limit))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MenuItemDTO convertToDTO(MenuItem item) {
        return MenuItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .description(item.getDescription())
                .imagePath(item.getImagePath())
                .category(MenuCategoryDTO.builder()
                        .id(item.getCategory().getId())
                        .name(item.getCategory().getName())
                        .build())
                .available(item.isAvailable())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }
}
