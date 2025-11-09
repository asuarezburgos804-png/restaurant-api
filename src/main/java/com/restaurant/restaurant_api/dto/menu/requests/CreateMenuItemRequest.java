package com.restaurant.restaurant_api.dto.menu.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateMenuItemRequest {
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String description;

    @NotNull(message = "La categoría es requerida")
    private Long categoryId;

    private String imagePath;

    private boolean available = true;

    // Constructores
    public CreateMenuItemRequest() {
        this.available = true;
    }

    public CreateMenuItemRequest(String name, BigDecimal price, String description, Long categoryId,
            String imagePath, boolean available) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.imagePath = imagePath;
        this.available = available;
    }

    // Getters y Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Builder pattern
    public static CreateMenuItemRequestBuilder builder() {
        return new CreateMenuItemRequestBuilder();
    }

    public static class CreateMenuItemRequestBuilder {
        private String name;
        private BigDecimal price;
        private String description;
        private Long categoryId;
        private String imagePath;
        private boolean available = true;

        CreateMenuItemRequestBuilder() {
        }

        public CreateMenuItemRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateMenuItemRequestBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public CreateMenuItemRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateMenuItemRequestBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public CreateMenuItemRequestBuilder imagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public CreateMenuItemRequestBuilder available(boolean available) {
            this.available = available;
            return this;
        }

        public CreateMenuItemRequest build() {
            return new CreateMenuItemRequest(name, price, description, categoryId, imagePath, available);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CreateMenuItemRequest that = (CreateMenuItemRequest) o;
        return available == that.available &&
                java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(price, that.price) &&
                java.util.Objects.equals(description, that.description) &&
                java.util.Objects.equals(categoryId, that.categoryId) &&
                java.util.Objects.equals(imagePath, that.imagePath);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, price, description, categoryId, imagePath, available);
    }

    @Override
    public String toString() {
        return "CreateMenuItemRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", imagePath='" + imagePath + '\'' +
                ", available=" + available +
                '}';
    }
}
