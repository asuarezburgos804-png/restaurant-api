package com.restaurant.restaurant_api.dto.inventory.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class StockOperationRequest {
    @NotNull(message = "Operation is required")
    @Pattern(regexp = "^(increase|decrease)$", message = "Operation must be either 'increase' or 'decrease'")
    private String operation;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    private Double amount;

    // Constructores
    public StockOperationRequest() {
    }

    public StockOperationRequest(String operation, Double amount) {
        this.operation = operation;
        this.amount = amount;
    }

    // Getters y Setters
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    // Builder pattern
    public static StockOperationRequestBuilder builder() {
        return new StockOperationRequestBuilder();
    }

    public static class StockOperationRequestBuilder {
        private String operation;
        private Double amount;

        StockOperationRequestBuilder() {
        }

        public StockOperationRequestBuilder operation(String operation) {
            this.operation = operation;
            return this;
        }

        public StockOperationRequestBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public StockOperationRequest build() {
            return new StockOperationRequest(operation, amount);
        }
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        StockOperationRequest that = (StockOperationRequest) o;
        return java.util.Objects.equals(operation, that.operation) &&
                java.util.Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(operation, amount);
    }

    @Override
    public String toString() {
        return "StockOperationRequest{" +
                "operation='" + operation + '\'' +
                ", amount=" + amount +
                '}';
    }
}
