package com.restaurant.restaurant_api.dto.sale.responses;

import com.restaurant.restaurant_api.dto.menu.responses.MenuItemDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaleItemDTO {
    private Long id;
    private MenuItemDTO menuItem;
    private BigDecimal priceAtTime;
    private LocalDateTime createdAt;

    // Constructores
    public SaleItemDTO() {
    }

    public SaleItemDTO(Long id, MenuItemDTO menuItem, BigDecimal priceAtTime, LocalDateTime createdAt) {
        this.id = id;
        this.menuItem = menuItem;
        this.priceAtTime = priceAtTime;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuItemDTO getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemDTO menuItem) {
        this.menuItem = menuItem;
    }

    public BigDecimal getPriceAtTime() {
        return priceAtTime;
    }

    public void setPriceAtTime(BigDecimal priceAtTime) {
        this.priceAtTime = priceAtTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Builder pattern
    public static SaleItemDTOBuilder builder() {
        return new SaleItemDTOBuilder();
    }

    public static class SaleItemDTOBuilder {
        private Long id;
        private MenuItemDTO menuItem;
        private BigDecimal priceAtTime;
        private LocalDateTime createdAt;

        SaleItemDTOBuilder() {
        }

        public SaleItemDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SaleItemDTOBuilder menuItem(MenuItemDTO menuItem) {
            this.menuItem = menuItem;
            return this;
        }

        public SaleItemDTOBuilder priceAtTime(BigDecimal priceAtTime) {
            this.priceAtTime = priceAtTime;
            return this;
        }

        public SaleItemDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SaleItemDTO build() {
            return new SaleItemDTO(id, menuItem, priceAtTime, createdAt);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SaleItemDTO that = (SaleItemDTO) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(menuItem, that.menuItem) &&
                java.util.Objects.equals(priceAtTime, that.priceAtTime) &&
                java.util.Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, menuItem, priceAtTime, createdAt);
    }

    @Override
    public String toString() {
        return "SaleItemDTO{" +
                "id=" + id +
                ", menuItem=" + menuItem +
                ", priceAtTime=" + priceAtTime +
                ", createdAt=" + createdAt +
                '}';
    }
}
