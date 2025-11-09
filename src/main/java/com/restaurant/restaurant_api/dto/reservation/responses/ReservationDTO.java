package com.restaurant.restaurant_api.dto.reservation.responses;

import com.restaurant.restaurant_api.models.Reservation.ReservationStatus;

public class ReservationDTO {
    private Long id;
    private String name;
    private String phone;
    private String date;
    private String time;
    private Integer guests;
    private ReservationStatus status;
    private String createdAt;
    private String updatedAt;

    // Constructores
    public ReservationDTO() {
    }

    public ReservationDTO(Long id, String name, String phone, String date, String time, Integer guests,
            ReservationStatus status, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.status = status;
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

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Builder pattern
    public static ReservationDTOBuilder builder() {
        return new ReservationDTOBuilder();
    }

    public static class ReservationDTOBuilder {
        private Long id;
        private String name;
        private String phone;
        private String date;
        private String time;
        private Integer guests;
        private ReservationStatus status;
        private String createdAt;
        private String updatedAt;

        ReservationDTOBuilder() {
        }

        public ReservationDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReservationDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ReservationDTOBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public ReservationDTOBuilder date(String date) {
            this.date = date;
            return this;
        }

        public ReservationDTOBuilder time(String time) {
            this.time = time;
            return this;
        }

        public ReservationDTOBuilder guests(Integer guests) {
            this.guests = guests;
            return this;
        }

        public ReservationDTOBuilder status(ReservationStatus status) {
            this.status = status;
            return this;
        }

        public ReservationDTOBuilder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ReservationDTOBuilder updatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ReservationDTO build() {
            return new ReservationDTO(id, name, phone, date, time, guests, status, createdAt, updatedAt);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ReservationDTO that = (ReservationDTO) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(phone, that.phone) &&
                java.util.Objects.equals(date, that.date) &&
                java.util.Objects.equals(time, that.time) &&
                java.util.Objects.equals(guests, that.guests) &&
                status == that.status &&
                java.util.Objects.equals(createdAt, that.createdAt) &&
                java.util.Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, phone, date, time, guests, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", guests=" + guests +
                ", status=" + status +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
