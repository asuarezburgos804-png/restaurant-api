package com.restaurant.restaurant_api.dto.menu.responses;

public class MenuCategoryDTO {
    private Long id;
    private String name;

    // Constructores
    public MenuCategoryDTO() {
    }

    public MenuCategoryDTO(Long id, String name) {
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
    public static MenuCategoryDTOBuilder builder() {
        return new MenuCategoryDTOBuilder();
    }

    public static class MenuCategoryDTOBuilder {
        private Long id;
        private String name;

        MenuCategoryDTOBuilder() {
        }

        public MenuCategoryDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MenuCategoryDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MenuCategoryDTO build() {
            return new MenuCategoryDTO(id, name);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MenuCategoryDTO that = (MenuCategoryDTO) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MenuCategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
