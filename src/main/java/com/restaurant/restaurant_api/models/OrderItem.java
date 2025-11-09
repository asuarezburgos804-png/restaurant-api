package com.restaurant.restaurant_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    @NotNull
    private MenuItem item;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Double subtotal;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Long id, MenuItem item, Integer quantity, Double subtotal, String notes, Order order) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.notes = notes;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static OrderItemBuilder builder() {
        return new OrderItemBuilder();
    }

    public static class OrderItemBuilder {
        private Long id;
        private MenuItem item;
        private Integer quantity;
        private Double subtotal;
        private String notes;
        private Order order;

        OrderItemBuilder() {
        }

        public OrderItemBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderItemBuilder item(MenuItem item) {
            this.item = item;
            return this;
        }

        public OrderItemBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItemBuilder subtotal(Double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public OrderItemBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public OrderItemBuilder order(Order order) {
            this.order = order;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(id, item, quantity, subtotal, notes, order);
        }
    }
}
