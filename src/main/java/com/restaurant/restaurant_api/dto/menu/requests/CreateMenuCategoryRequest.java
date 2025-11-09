package com.restaurant.restaurant_api.dto.menu.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateMenuCategoryRequest {
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    // Constructores
    public CreateMenuCategoryRequest() {
    }

    public CreateMenuCategoryRequest(String name) {
        this.name = name;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Builder pattern
    public static CreateMenuCategoryRequestBuilder builder() {
        return new CreateMenuCategoryRequestBuilder();
    }

    public static class CreateMenuCategoryRequestBuilder {
        private String name;

        CreateMenuCategoryRequestBuilder() {
        }

        public CreateMenuCategoryRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateMenuCategoryRequest build() {
            return new CreateMenuCategoryRequest(name);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CreateMenuCategoryRequest that = (CreateMenuCategoryRequest) o;
        return java.util.Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CreateMenuCategoryRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
