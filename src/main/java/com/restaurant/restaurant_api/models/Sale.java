package com.restaurant.restaurant_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    @NotNull
    @Positive
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> items = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if (saleDate == null) {
            saleDate = createdAt;
        }
        if (items == null) {
            items = new ArrayList<>();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void calculateTotalPrice() {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.totalPrice = items.stream()
                .map(SaleItem::getPriceAtTime)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Constructores
    public Sale() {
        this.items = new ArrayList<>();
    }

    public Sale(Long id, String name, String description, LocalDateTime saleDate, BigDecimal totalPrice,
            List<SaleItem> items, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.clear();
        if (items != null) {
            items.forEach(this::addItem);
        }
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
    public static SaleBuilder builder() {
        return new SaleBuilder();
    }

    public static class SaleBuilder {
        private Long id;
        private String name;
        private String description;
        private LocalDateTime saleDate;
        private BigDecimal totalPrice;
        private List<SaleItem> items = new ArrayList<>();
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        SaleBuilder() {
        }

        public SaleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SaleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SaleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SaleBuilder saleDate(LocalDateTime saleDate) {
            this.saleDate = saleDate;
            return this;
        }

        public SaleBuilder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public SaleBuilder items(List<SaleItem> items) {
            this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
            return this;
        }

        public SaleBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SaleBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Sale build() {
            return new Sale(id, name, description, saleDate, totalPrice, items, createdAt, updatedAt);
        }
    }

    // Métodos de gestión de items
    public void addItem(SaleItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        items.add(item);
        item.setSale(this);
    }

    public void removeItem(SaleItem item) {
        if (this.items != null) {
            items.remove(item);
            item.setSale(null);
        }
    }

    public void clearItems() {
        if (this.items != null) {
            items.forEach(item -> item.setSale(null));
            items.clear();
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Sale sale = (Sale) o;
        return java.util.Objects.equals(id, sale.id) &&
                java.util.Objects.equals(name, sale.name) &&
                java.util.Objects.equals(description, sale.description) &&
                java.util.Objects.equals(saleDate, sale.saleDate) &&
                java.util.Objects.equals(totalPrice, sale.totalPrice) &&
                java.util.Objects.equals(createdAt, sale.createdAt) &&
                java.util.Objects.equals(updatedAt, sale.updatedAt);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, description, saleDate, totalPrice, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", saleDate=" + saleDate +
                ", totalPrice=" + totalPrice +
                ", itemsCount=" + (items != null ? items.size() : 0) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
