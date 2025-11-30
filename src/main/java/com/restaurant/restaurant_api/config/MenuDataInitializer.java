package com.restaurant.restaurant_api.config;

import com.restaurant.restaurant_api.models.MenuItemCategory;
import com.restaurant.restaurant_api.repositories.MenuCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MenuDataInitializer {
    private final MenuCategoryRepository menuCategoryRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/images/menu";

    public MenuDataInitializer(MenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Bean
    public CommandLineRunner initializeMenuData() {
        return args -> {
            // Crear directorio de imágenes si no existe
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                    System.out.println("Directorio de imágenes creado: " + UPLOAD_DIR);
                }
            } catch (Exception e) {
                System.err.println("Error al crear directorio de imágenes: " + e.getMessage());
            }

            // Inicializar categorías si no existen
            if (menuCategoryRepository.count() == 0) {
                List<String> categories = Arrays.asList(
                        "Hamburguesa",
                        "Pizza",
                        "Bebida",
                        "Complemento",
                        "Postre",
                        "Combo");

                categories.forEach(categoryName -> {
                    MenuItemCategory category = MenuItemCategory.builder()
                            .name(categoryName)
                            .build();
                    menuCategoryRepository.save(category);
                });

                System.out.println("Categorías del menú inicializadas");
            }
        };
    }
}
