package com.restaurant.restaurant_api.controllers;

import com.restaurant.restaurant_api.models.Order;
import com.restaurant.restaurant_api.models.OrderStatus;
import com.restaurant.restaurant_api.models.User;
import com.restaurant.restaurant_api.repositories.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/waiter/orders")
@CrossOrigin(origins = "http://localhost:55423")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @Valid @RequestBody Order order,
            @AuthenticationPrincipal User waiter) {
        order.setWaiter(waiter);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @GetMapping
    public ResponseEntity<Page<Order>> getOrders(
            @AuthenticationPrincipal User waiter,
            Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findAll(pageable));
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getMyOrders(@AuthenticationPrincipal User waiter) {
        return ResponseEntity.ok(orderRepository.findByWaiter(waiter));
    }

    @GetMapping("/table/{tableNumber}")
    public ResponseEntity<List<Order>> getOrdersByTable(@PathVariable Integer tableNumber) {
        return ResponseEntity.ok(orderRepository.findByTableNumber(tableNumber));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Order>> getOrdersByStatus(
            @PathVariable OrderStatus status,
            Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findByStatus(status, pageable));
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @GetMapping("/today")
    public ResponseEntity<List<Order>> getTodayOrders() {
        return ResponseEntity.ok(orderRepository.findTodayOrders());
    }
}
