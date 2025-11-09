package com.restaurant.restaurant_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "menu_categories")
public class MenuItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    // Constructores
    public MenuItemCategory() {
    }

    public MenuItemCategory(Long id, String name) {
        this.id = id;
        this.name = name;
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

    // Builder pattern
    public static MenuItemCategoryBuilder builder() {
        return new MenuItemCategoryBuilder();
    }

    public static class MenuItemCategoryBuilder {
        private Long id;
        private String name;

        MenuItemCategoryBuilder() {
        }

        public MenuItemCategoryBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MenuItemCategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MenuItemCategory build() {
            return new MenuItemCategory(id, name);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MenuItemCategory category = (MenuItemCategory) o;
        return java.util.Objects.equals(id, category.id) &&
                java.util.Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MenuItemCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
