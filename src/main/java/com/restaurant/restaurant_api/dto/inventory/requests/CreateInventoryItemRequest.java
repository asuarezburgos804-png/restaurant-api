package com.restaurant.restaurant_api.dto.inventory.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateInventoryItemRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Unit price is required")
    @Min(value = 0, message = "Unit price must be greater than or equal to 0")
    private BigDecimal unitPrice;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    private Double stock;

    private Long measurementUnitId;

    // Constructores
    public CreateInventoryItemRequest() {
    }

    public CreateInventoryItemRequest(String name, BigDecimal unitPrice, Double stock, Long measurementUnitId) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.measurementUnitId = measurementUnitId;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Long getMeasurementUnitId() {
        return measurementUnitId;
    }

    public void setMeasurementUnitId(Long measurementUnitId) {
        this.measurementUnitId = measurementUnitId;
    }

    // Builder pattern
    public static CreateInventoryItemRequestBuilder builder() {
        return new CreateInventoryItemRequestBuilder();
    }

    public static class CreateInventoryItemRequestBuilder {
        private String name;
        private BigDecimal unitPrice;
        private Double stock;
        private Long measurementUnitId;

        CreateInventoryItemRequestBuilder() {
        }

        public CreateInventoryItemRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateInventoryItemRequestBuilder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public CreateInventoryItemRequestBuilder stock(Double stock) {
            this.stock = stock;
            return this;
        }

        public CreateInventoryItemRequestBuilder measurementUnitId(Long measurementUnitId) {
            this.measurementUnitId = measurementUnitId;
            return this;
        }

        public CreateInventoryItemRequest build() {
            return new CreateInventoryItemRequest(name, unitPrice, stock, measurementUnitId);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CreateInventoryItemRequest that = (CreateInventoryItemRequest) o;
        return java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(unitPrice, that.unitPrice) &&
                java.util.Objects.equals(stock, that.stock) &&
                java.util.Objects.equals(measurementUnitId, that.measurementUnitId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, unitPrice, stock, measurementUnitId);
    }

    @Override
    public String toString() {
        return "CreateInventoryItemRequest{" +
                "name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", stock=" + stock +
                ", measurementUnitId=" + measurementUnitId +
                '}';
    }
}
