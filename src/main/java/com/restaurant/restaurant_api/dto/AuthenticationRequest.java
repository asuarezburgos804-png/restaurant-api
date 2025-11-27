package com.restaurant.restaurant_api.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthenticationRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    // Constructores
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Builder pattern
    public static AuthenticationRequestBuilder builder() {
        return new AuthenticationRequestBuilder();
    }

    public static class AuthenticationRequestBuilder {
        private String username;
        private String password;

        AuthenticationRequestBuilder() {
        }

        public AuthenticationRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AuthenticationRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationRequest build() {
            return new AuthenticationRequest(username, password);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuthenticationRequest that = (AuthenticationRequest) o;
        return java.util.Objects.equals(username, that.username) &&
                java.util.Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}
