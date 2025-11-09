package com.restaurant.restaurant_api.dto.sale.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime saleDate;
    private BigDecimal totalPrice;
    private List<SaleItemDTO> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructores
    public SaleDTO() {
        this.items = new ArrayList<>();
    }

    public SaleDTO(Long id, String name, String description, LocalDateTime saleDate, BigDecimal totalPrice,
            List<SaleItemDTO> items, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SaleItemDTO> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<SaleItemDTO> items) {
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
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
    public static SaleDTOBuilder builder() {
        return new SaleDTOBuilder();
    }

    public static class SaleDTOBuilder {
        private Long id;
        private String name;
        private String description;
        private LocalDateTime saleDate;
        private BigDecimal totalPrice;
        private List<SaleItemDTO> items = new ArrayList<>();
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        SaleDTOBuilder() {
        }

        public SaleDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SaleDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SaleDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SaleDTOBuilder saleDate(LocalDateTime saleDate) {
            this.saleDate = saleDate;
            return this;
        }

        public SaleDTOBuilder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public SaleDTOBuilder items(List<SaleItemDTO> items) {
            this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
            return this;
        }

        public SaleDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SaleDTOBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public SaleDTO build() {
            return new SaleDTO(id, name, description, saleDate, totalPrice, items, createdAt, updatedAt);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SaleDTO saleDTO = (SaleDTO) o;
        return java.util.Objects.equals(id, saleDTO.id) &&
                java.util.Objects.equals(name, saleDTO.name) &&
                java.util.Objects.equals(description, saleDTO.description) &&
                java.util.Objects.equals(saleDate, saleDTO.saleDate) &&
                java.util.Objects.equals(totalPrice, saleDTO.totalPrice) &&
                java.util.Objects.equals(items, saleDTO.items) &&
                java.util.Objects.equals(createdAt, saleDTO.createdAt) &&
                java.util.Objects.equals(updatedAt, saleDTO.updatedAt);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, description, saleDate, totalPrice, items, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "SaleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", saleDate=" + saleDate +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
