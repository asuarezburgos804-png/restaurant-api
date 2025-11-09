package com.restaurant.restaurant_api.dto.menu.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MenuItemDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String imagePath;
    private MenuCategoryDTO category;
    private String description;
    private boolean available;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructores
    public MenuItemDTO() {
    }

    public MenuItemDTO(Long id, String name, BigDecimal price, String imagePath, MenuCategoryDTO category,
            String description, boolean available, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;
        this.description = description;
        this.available = available;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public MenuCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(MenuCategoryDTO category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Builder pattern
    public static MenuItemDTOBuilder builder() {
        return new MenuItemDTOBuilder();
    }

    public static class MenuItemDTOBuilder {
        private Long id;
        private String name;
        private BigDecimal price;
        private String imagePath;
        private MenuCategoryDTO category;
        private String description;
        private boolean available;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        MenuItemDTOBuilder() {
        }

        public MenuItemDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MenuItemDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MenuItemDTOBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public MenuItemDTOBuilder imagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public MenuItemDTOBuilder category(MenuCategoryDTO category) {
            this.category = category;
            return this;
        }

        public MenuItemDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MenuItemDTOBuilder available(boolean available) {
            this.available = available;
            return this;
        }

        public MenuItemDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MenuItemDTOBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public MenuItemDTO build() {
            return new MenuItemDTO(id, name, price, imagePath, category, description, available, createdAt, updatedAt);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MenuItemDTO that = (MenuItemDTO) o;
        return available == that.available &&
                java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(price, that.price) &&
                java.util.Objects.equals(imagePath, that.imagePath) &&
                java.util.Objects.equals(category, that.category) &&
                java.util.Objects.equals(description, that.description) &&
                java.util.Objects.equals(createdAt, that.createdAt) &&
                java.util.Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, price, imagePath, category, description, available, createdAt,
                updatedAt);
    }

    @Override
    public String toString() {
        return "MenuItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
