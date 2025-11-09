package com.restaurant.restaurant_api.controllers;

import com.restaurant.restaurant_api.models.Order;
import com.restaurant.restaurant_api.models.OrderStatus;
import com.restaurant.restaurant_api.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/kitchen")
@CrossOrigin(origins = "http://localhost:4200")
public class KitchenController {
    private final OrderRepository orderRepository;

    @Autowired
    public KitchenController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders/pending")
    public ResponseEntity<List<Order>> getPendingOrders() {
        return ResponseEntity.ok(orderRepository.findByStatusOrderByCreatedAtAsc(OrderStatus.PENDING));
    }

    @GetMapping("/orders/in-progress")
    public ResponseEntity<List<Order>> getInProgressOrders() {
        return ResponseEntity.ok(orderRepository.findByStatusOrderByCreatedAtAsc(OrderStatus.IN_PROGRESS));
    }

    @PatchMapping("/orders/{orderId}/start")
    public ResponseEntity<Order> startPreparingOrder(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Order is not in PENDING status");
        }

        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @PatchMapping("/orders/{orderId}/complete")
    public ResponseEntity<Order> completeOrder(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.IN_PROGRESS) {
            throw new RuntimeException("Order is not in IN_PROGRESS status");
        }

        order.setStatus(OrderStatus.READY);
        order.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @GetMapping("/orders/today")
    public ResponseEntity<List<Order>> getTodayOrders() {
        return ResponseEntity.ok(orderRepository.findTodayOrders());
    }

    @GetMapping("/orders/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable OrderStatus status) {
        return ResponseEntity.ok(orderRepository.findByStatusOrderByCreatedAtAsc(status));
    }
}
