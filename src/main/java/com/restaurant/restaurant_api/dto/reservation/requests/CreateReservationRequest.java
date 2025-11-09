package com.restaurant.restaurant_api.dto.reservation.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class CreateReservationRequest {
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "El teléfono es requerido")
    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos")
    private String phone;

    @NotBlank(message = "La fecha es requerida")
    private String date;

    @NotBlank(message = "La hora es requerida")
    private String time;

    @NotNull(message = "El número de personas es requerido")
    @Positive(message = "El número de personas debe ser mayor a 0")
    private Integer guests;

    // Constructores
    public CreateReservationRequest() {
    }

    public CreateReservationRequest(String name, String phone, String date, String time, Integer guests) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.guests = guests;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    // Builder pattern
    public static CreateReservationRequestBuilder builder() {
        return new CreateReservationRequestBuilder();
    }

    public static class CreateReservationRequestBuilder {
        private String name;
        private String phone;
        private String date;
        private String time;
        private Integer guests;

        CreateReservationRequestBuilder() {
        }

        public CreateReservationRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CreateReservationRequestBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public CreateReservationRequestBuilder date(String date) {
            this.date = date;
            return this;
        }

        public CreateReservationRequestBuilder time(String time) {
            this.time = time;
            return this;
        }

        public CreateReservationRequestBuilder guests(Integer guests) {
            this.guests = guests;
            return this;
        }

        public CreateReservationRequest build() {
            return new CreateReservationRequest(name, phone, date, time, guests);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CreateReservationRequest that = (CreateReservationRequest) o;
        return java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(phone, that.phone) &&
                java.util.Objects.equals(date, that.date) &&
                java.util.Objects.equals(time, that.time) &&
                java.util.Objects.equals(guests, that.guests);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, phone, date, time, guests);
    }

    @Override
    public String toString() {
        return "CreateReservationRequest{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", guests=" + guests +
                '}';
    }
}
