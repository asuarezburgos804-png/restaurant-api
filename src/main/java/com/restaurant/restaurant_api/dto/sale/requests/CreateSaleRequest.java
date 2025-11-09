package com.restaurant.restaurant_api.dto.sale.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CreateSaleRequest {
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String description;

    @NotEmpty(message = "Debe seleccionar al menos un item")
    private List<Long> itemIds;

    // Constructores
    public CreateSaleRequest() {
        this.itemIds = new ArrayList<>();
    }

    public CreateSaleRequest(String name, String description, List<Long> itemIds) {
        this.name = name;
        this.description = description;
        this.itemIds = itemIds != null ? new ArrayList<>(itemIds) : new ArrayList<>();
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds != null ? new ArrayList<>(itemIds) : new ArrayList<>();
    }

    // Builder pattern
    public static CreateSaleRequestBuilder builder() {
        return new CreateSaleRequestBuilder();
    }

    public static class CreateSaleRequestBuilder {
        private String name;
        private String description;
        private List<Long> itemIds = new ArrayList<>();

        CreateSaleRequestBuilder() {
        }

        public CreateSaleRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateSaleRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CreateSaleRequestBuilder itemIds(List<Long> itemIds) {
            this.itemIds = itemIds != null ? new ArrayList<>(itemIds) : new ArrayList<>();
            return this;
        }

        public CreateSaleRequest build() {
            return new CreateSaleRequest(name, description, itemIds);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CreateSaleRequest that = (CreateSaleRequest) o;
        return java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(description, that.description) &&
                java.util.Objects.equals(itemIds, that.itemIds);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, description, itemIds);
    }

    @Override
    public String toString() {
        return "CreateSaleRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", itemIds=" + itemIds +
                '}';
    }
}
