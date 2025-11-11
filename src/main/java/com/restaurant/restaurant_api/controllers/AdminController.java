package com.restaurant.restaurant_api.controllers;


import com.restaurant.restaurant_api.models.MenuItem;
import com.restaurant.restaurant_api.models.Order;
import com.restaurant.restaurant_api.models.OrderStatus;
import com.restaurant.restaurant_api.models.User;
import com.restaurant.restaurant_api.repositories.MenuItemRepository;
import com.restaurant.restaurant_api.repositories.OrderRepository;
import com.restaurant.restaurant_api.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:55423")
public class AdminController {
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AdminController(
            UserRepository userRepository,
            MenuItemRepository menuItemRepository,
            OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
    }

    // User Management
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PatchMapping("/users/{userId}/activate")
    public ResponseEntity<User> toggleUserActive(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(!user.isActive());
        return ResponseEntity.ok(userRepository.save(user));
    }

    // Menu Management
    @GetMapping("/menu")
    public ResponseEntity<Page<MenuItem>> getAllMenuItems(Pageable pageable) {
        return ResponseEntity.ok(menuItemRepository.findAll(pageable));
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuItem> createMenuItem(@Valid @RequestBody MenuItem menuItem) {
        return ResponseEntity.ok(menuItemRepository.save(menuItem));
    }

    @PutMapping("/menu/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable Long itemId,
            @Valid @RequestBody MenuItem menuItem) {
        if (!menuItemRepository.existsById(itemId)) {
            throw new RuntimeException("Menu item not found");
        }
        menuItem.setId(itemId);
        return ResponseEntity.ok(menuItemRepository.save(menuItem));
    }

    @DeleteMapping("/menu/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long itemId) {
        menuItemRepository.deleteById(itemId);
        return ResponseEntity.ok().build();
    }

    // Sales and Analytics
    @GetMapping("/sales/summary")
    public ResponseEntity<Double> getSalesSummary(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(orderRepository.calculateTotalSales(startDate, endDate));
    }

    @GetMapping("/orders/count-by-status")
    public ResponseEntity<Long> getOrderCountByStatus(@RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderRepository.countByStatus(status));
    }

    @GetMapping("/orders/today")
    public ResponseEntity<List<Order>> getTodayOrders() {
        return ResponseEntity.ok(orderRepository.findTodayOrders());
    }

    @GetMapping("/orders")
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findAll(pageable));
    }
}
