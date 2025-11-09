package com.restaurant.restaurant_api.dto.inventory.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InventoryItemDTO {
    private Long id;
    private String name;
    private BigDecimal unitPrice;
    private Double stock;
    private MeasurementUnitDTO measurementUnit;
    private BigDecimal totalPrice;
    private LocalDateTime lastUpdated;
    private LocalDateTime createdAt;

    // Constructores
    public InventoryItemDTO() {
    }

    public InventoryItemDTO(Long id, String name, BigDecimal unitPrice, Double stock,
            MeasurementUnitDTO measurementUnit, BigDecimal totalPrice, LocalDateTime lastUpdated,
            LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.measurementUnit = measurementUnit;
        this.totalPrice = totalPrice;
        this.lastUpdated = lastUpdated;
        this.createdAt = createdAt;
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

    public MeasurementUnitDTO getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnitDTO measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Builder pattern
    public static InventoryItemDTOBuilder builder() {
        return new InventoryItemDTOBuilder();
    }

    public static class InventoryItemDTOBuilder {
        private Long id;
        private String name;
        private BigDecimal unitPrice;
        private Double stock;
        private MeasurementUnitDTO measurementUnit;
        private BigDecimal totalPrice;
        private LocalDateTime lastUpdated;
        private LocalDateTime createdAt;

        InventoryItemDTOBuilder() {
        }

        public InventoryItemDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public InventoryItemDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public InventoryItemDTOBuilder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public InventoryItemDTOBuilder stock(Double stock) {
            this.stock = stock;
            return this;
        }

        public InventoryItemDTOBuilder measurementUnit(MeasurementUnitDTO measurementUnit) {
            this.measurementUnit = measurementUnit;
            return this;
        }

        public InventoryItemDTOBuilder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public InventoryItemDTOBuilder lastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public InventoryItemDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public InventoryItemDTO build() {
            return new InventoryItemDTO(id, name, unitPrice, stock, measurementUnit, totalPrice, lastUpdated,
                    createdAt);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InventoryItemDTO that = (InventoryItemDTO) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(unitPrice, that.unitPrice) &&
                java.util.Objects.equals(stock, that.stock) &&
                java.util.Objects.equals(measurementUnit, that.measurementUnit) &&
                java.util.Objects.equals(totalPrice, that.totalPrice) &&
                java.util.Objects.equals(lastUpdated, that.lastUpdated) &&
                java.util.Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, unitPrice, stock, measurementUnit, totalPrice, lastUpdated, createdAt);
    }

    @Override
    public String toString() {
        return "InventoryItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", stock=" + stock +
                ", measurementUnit=" + measurementUnit +
                ", totalPrice=" + totalPrice +
                ", lastUpdated=" + lastUpdated +
                ", createdAt=" + createdAt +
                '}';
    }
}
