package com.restaurant.restaurant_api.services;

import com.restaurant.restaurant_api.dto.menu.requests.CreateMenuCategoryRequest;
import com.restaurant.restaurant_api.dto.menu.responses.MenuCategoryDTO;
import com.restaurant.restaurant_api.models.MenuItemCategory;
import com.restaurant.restaurant_api.repositories.MenuCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuCategoryService {
    private final MenuCategoryRepository categoryRepository;

    @Autowired
    public MenuCategoryService(MenuCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public List<MenuCategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public MenuCategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
    }

    @Transactional
    public MenuCategoryDTO createCategory(CreateMenuCategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Ya existe una categoría con este nombre");
        }

        MenuItemCategory category = MenuItemCategory.builder()
                .name(request.getName())
                .build();

        return convertToDTO(categoryRepository.save(category));
    }

    @Transactional
    public MenuCategoryDTO updateCategory(Long id, CreateMenuCategoryRequest request) {
        MenuItemCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        if (!category.getName().equalsIgnoreCase(request.getName()) &&
                categoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Ya existe una categoría con este nombre");
        }

        category.setName(request.getName());
        return convertToDTO(categoryRepository.save(category));
    }

    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada");
        }
        categoryRepository.deleteById(id);
    }

    private MenuCategoryDTO convertToDTO(MenuItemCategory category) {
        return MenuCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
