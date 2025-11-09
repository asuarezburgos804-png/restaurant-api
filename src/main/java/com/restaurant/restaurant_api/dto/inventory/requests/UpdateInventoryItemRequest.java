package com.restaurant.restaurant_api.dto.inventory.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class UpdateInventoryItemRequest {
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Min(value = 0, message = "Unit price must be greater than or equal to 0")
    private BigDecimal unitPrice;

    private Long measurementUnitId;

    // Constructores
    public UpdateInventoryItemRequest() {
    }

    public UpdateInventoryItemRequest(String name, BigDecimal unitPrice, Long measurementUnitId) {
        this.name = name;
        this.unitPrice = unitPrice;
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

    public Long getMeasurementUnitId() {
        return measurementUnitId;
    }

    public void setMeasurementUnitId(Long measurementUnitId) {
        this.measurementUnitId = measurementUnitId;
    }

    // Builder pattern
    public static UpdateInventoryItemRequestBuilder builder() {
        return new UpdateInventoryItemRequestBuilder();
    }

    public static class UpdateInventoryItemRequestBuilder {
        private String name;
        private BigDecimal unitPrice;
        private Long measurementUnitId;

        UpdateInventoryItemRequestBuilder() {
        }

        public UpdateInventoryItemRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateInventoryItemRequestBuilder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public UpdateInventoryItemRequestBuilder measurementUnitId(Long measurementUnitId) {
            this.measurementUnitId = measurementUnitId;
            return this;
        }

        public UpdateInventoryItemRequest build() {
            return new UpdateInventoryItemRequest(name, unitPrice, measurementUnitId);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UpdateInventoryItemRequest that = (UpdateInventoryItemRequest) o;
        return java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(unitPrice, that.unitPrice) &&
                java.util.Objects.equals(measurementUnitId, that.measurementUnitId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, unitPrice, measurementUnitId);
    }

    @Override
    public String toString() {
        return "UpdateInventoryItemRequest{" +
                "name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", measurementUnitId=" + measurementUnitId +
                '}';
    }
}
