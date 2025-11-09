package com.restaurant.restaurant_api.dto.reservation.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class UpdateReservationRequest {
    private String name;

    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos")
    private String phone;

    private String date;

    private String time;

    @Positive(message = "El número de personas debe ser mayor a 0")
    private Integer guests;

    private String status; // PENDING, CONFIRMED, CANCELLED

    // Constructores
    public UpdateReservationRequest() {
    }

    public UpdateReservationRequest(String name, String phone, String date, String time, Integer guests,
            String status) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Builder pattern
    public static UpdateReservationRequestBuilder builder() {
        return new UpdateReservationRequestBuilder();
    }

    public static class UpdateReservationRequestBuilder {
        private String name;
        private String phone;
        private String date;
        private String time;
        private Integer guests;
        private String status;

        UpdateReservationRequestBuilder() {
        }

        public UpdateReservationRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateReservationRequestBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UpdateReservationRequestBuilder date(String date) {
            this.date = date;
            return this;
        }

        public UpdateReservationRequestBuilder time(String time) {
            this.time = time;
            return this;
        }

        public UpdateReservationRequestBuilder guests(Integer guests) {
            this.guests = guests;
            return this;
        }

        public UpdateReservationRequestBuilder status(String status) {
            this.status = status;
            return this;
        }

        public UpdateReservationRequest build() {
            return new UpdateReservationRequest(name, phone, date, time, guests, status);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UpdateReservationRequest that = (UpdateReservationRequest) o;
        return java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(phone, that.phone) &&
                java.util.Objects.equals(date, that.date) &&
                java.util.Objects.equals(time, that.time) &&
                java.util.Objects.equals(guests, that.guests) &&
                java.util.Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, phone, date, time, guests, status);
    }

    @Override
    public String toString() {
        return "UpdateReservationRequest{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", guests=" + guests +
                ", status='" + status + '\'' +
                '}';
    }
}
