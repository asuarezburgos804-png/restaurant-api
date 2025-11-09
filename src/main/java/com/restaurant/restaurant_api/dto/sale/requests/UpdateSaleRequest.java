package com.restaurant.restaurant_api.dto.sale.requests;

import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UpdateSaleRequest {
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String description;

    private List<Long> itemIds;

    // Constructores
    public UpdateSaleRequest() {
        this.itemIds = new ArrayList<>();
    }

    public UpdateSaleRequest(String name, String description, List<Long> itemIds) {
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
    public static UpdateSaleRequestBuilder builder() {
        return new UpdateSaleRequestBuilder();
    }

    public static class UpdateSaleRequestBuilder {
        private String name;
        private String description;
        private List<Long> itemIds = new ArrayList<>();

        UpdateSaleRequestBuilder() {
        }

        public UpdateSaleRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateSaleRequestBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UpdateSaleRequestBuilder itemIds(List<Long> itemIds) {
            this.itemIds = itemIds != null ? new ArrayList<>(itemIds) : new ArrayList<>();
            return this;
        }

        public UpdateSaleRequest build() {
            return new UpdateSaleRequest(name, description, itemIds);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UpdateSaleRequest that = (UpdateSaleRequest) o;
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
        return "UpdateSaleRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", itemIds=" + itemIds +
                '}';
    }
}
