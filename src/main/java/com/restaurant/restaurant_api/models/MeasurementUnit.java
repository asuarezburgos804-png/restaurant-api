package com.restaurant.restaurant_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "measurement_units")
public class MeasurementUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 10)
    private String symbol;

    public MeasurementUnit() {
    }

    public MeasurementUnit(Long id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

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

    public static MeasurementUnitBuilder builder() {
        return new MeasurementUnitBuilder();
    }

    public static class MeasurementUnitBuilder {
        private Long id;
        private String name;
        private String symbol;

        MeasurementUnitBuilder() {
        }

        public MeasurementUnitBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MeasurementUnitBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MeasurementUnitBuilder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public MeasurementUnit build() {
            return new MeasurementUnit(id, name, symbol);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MeasurementUnit that = (MeasurementUnit) o;
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
        return "MeasurementUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
