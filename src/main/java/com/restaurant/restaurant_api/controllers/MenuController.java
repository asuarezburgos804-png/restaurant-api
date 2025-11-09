package com.restaurant.restaurant_api.controllers;

import com.restaurant.restaurant_api.dto.menu.requests.CreateMenuCategoryRequest;
import com.restaurant.restaurant_api.dto.menu.requests.CreateMenuItemRequest;
import com.restaurant.restaurant_api.dto.menu.requests.UpdateMenuItemRequest;
import com.restaurant.restaurant_api.dto.menu.responses.MenuCategoryDTO;
import com.restaurant.restaurant_api.dto.menu.responses.MenuItemDTO;
import com.restaurant.restaurant_api.services.MenuCategoryService;
import com.restaurant.restaurant_api.services.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:49378" })
public class MenuController {
    private final MenuItemService menuItemService;
    private final MenuCategoryService categoryService;

    @Autowired
    public MenuController(MenuItemService menuItemService, MenuCategoryService categoryService) {
        this.menuItemService = menuItemService;
        this.categoryService = categoryService;
    }

    // Endpoints públicos para clientes
    @GetMapping("/items")
    public ResponseEntity<Page<MenuItemDTO>> getAllMenuItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean available,
            Pageable pageable) {
        try {
            Page<MenuItemDTO> items = menuItemService.getAllItems(name, categoryId, available, pageable);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            System.err.println("Error getting menu items: " + e.getMessage());
            throw e;
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getItemById(id));
    }

    @GetMapping("/items/category/{categoryId}")
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(menuItemService.getItemsByCategory(categoryId));
    }

    @GetMapping("/items/available")
    public ResponseEntity<List<MenuItemDTO>> getAvailableItems() {
        return ResponseEntity.ok(menuItemService.getAvailableItems());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<MenuCategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<MenuCategoryDTO> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // Endpoints protegidos para administración
    @PostMapping("/items")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuItemDTO> createMenuItem(@Valid @RequestBody CreateMenuItemRequest request) {
        try {
            System.out.println("Creating menu item with request: " + request);
            MenuItemDTO createdItem = menuItemService.createItem(request);
            System.out.println("Menu item created: " + createdItem);
            return ResponseEntity.ok(createdItem);
        } catch (Exception e) {
            System.err.println("Error creating menu item: " + e.getMessage());
            throw e;
        }
    }

    @PutMapping("/items/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuItemDTO> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMenuItemRequest request) {
        return ResponseEntity.ok(menuItemService.updateItem(id, request));
    }

    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuCategoryDTO> createCategory(@Valid @RequestBody CreateMenuCategoryRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuCategoryDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CreateMenuCategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener items recientemente actualizados
    @GetMapping("/items/recent")
    public ResponseEntity<List<MenuItemDTO>> getRecentlyUpdatedItems(
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(menuItemService.getRecentlyUpdated(limit));
    }
}
