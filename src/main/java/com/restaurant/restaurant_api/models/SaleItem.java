package com.restaurant.restaurant_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sale_items")
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @NotNull
    @Positive
    @Column(name = "price_at_time")
    private BigDecimal priceAtTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (priceAtTime == null && menuItem != null) {
            priceAtTime = menuItem.getPrice();
        }
    }

    // Constructores
    public SaleItem() {
    }

    public SaleItem(Long id, Sale sale, MenuItem menuItem, BigDecimal priceAtTime, LocalDateTime createdAt) {
        this.id = id;
        this.sale = sale;
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

    public Sale getSale() {
        return sale;
    }

    // setSale tiene implementación personalizada para manejar la relación
    // bidireccional
    public void setSale(Sale sale) {
        if (this.sale != null && this.sale != sale) {
            List<SaleItem> items = this.sale.getItems();
            if (items != null) {
                items.remove(this);
            }
        }
        this.sale = sale;
        if (sale != null) {
            List<SaleItem> items = sale.getItems();
            if (items != null && !items.contains(this)) {
                items.add(this);
            }
        }
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
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
    public static SaleItemBuilder builder() {
        return new SaleItemBuilder();
    }

    public static class SaleItemBuilder {
        private Long id;
        private Sale sale;
        private MenuItem menuItem;
        private BigDecimal priceAtTime;
        private LocalDateTime createdAt;

        SaleItemBuilder() {
        }

        public SaleItemBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SaleItemBuilder sale(Sale sale) {
            this.sale = sale;
            return this;
        }

        public SaleItemBuilder menuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
            return this;
        }

        public SaleItemBuilder priceAtTime(BigDecimal priceAtTime) {
            this.priceAtTime = priceAtTime;
            return this;
        }

        public SaleItemBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SaleItem build() {
            return new SaleItem(id, sale, menuItem, priceAtTime, createdAt);
        }
    }

    // equals, hashCode y toString (excluyendo el campo sale para evitar recursión
    // infinita)
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SaleItem saleItem = (SaleItem) o;
        return java.util.Objects.equals(id, saleItem.id) &&
                java.util.Objects.equals(menuItem, saleItem.menuItem) &&
                java.util.Objects.equals(priceAtTime, saleItem.priceAtTime) &&
                java.util.Objects.equals(createdAt, saleItem.createdAt);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, menuItem, priceAtTime, createdAt);
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "id=" + id +
                ", menuItem=" + menuItem +
                ", priceAtTime=" + priceAtTime +
                ", createdAt=" + createdAt +
                '}';
    }
}
