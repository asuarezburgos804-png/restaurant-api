package com.restaurant.restaurant_api.dto.inventory.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MeasurementUnitDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Symbol is required")
    @Size(min = 1, max = 10, message = "Symbol must be between 1 and 10 characters")
    private String symbol;

    // Constructores
    public MeasurementUnitDTO() {
    }

    public MeasurementUnitDTO(Long id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // Builder pattern
    public static MeasurementUnitDTOBuilder builder() {
        return new MeasurementUnitDTOBuilder();
    }

    public static class MeasurementUnitDTOBuilder {
        private Long id;
        private String name;
        private String symbol;

        MeasurementUnitDTOBuilder() {
        }

        public MeasurementUnitDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MeasurementUnitDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MeasurementUnitDTOBuilder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public MeasurementUnitDTO build() {
            return new MeasurementUnitDTO(id, name, symbol);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MeasurementUnitDTO that = (MeasurementUnitDTO) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, symbol);
    }

    @Override
    public String toString() {
        return "MeasurementUnitDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
