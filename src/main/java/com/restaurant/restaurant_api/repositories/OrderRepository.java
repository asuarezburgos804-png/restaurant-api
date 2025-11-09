package com.restaurant.restaurant_api.repositories;

import com.restaurant.restaurant_api.models.Order;
import com.restaurant.restaurant_api.models.OrderStatus;
import com.restaurant.restaurant_api.models.OrderType;
import com.restaurant.restaurant_api.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);

    Page<Order> findByType(OrderType type, Pageable pageable);

    List<Order> findByWaiter(User waiter);

    @Query("SELECT o FROM Order o WHERE o.createdAt >= :startDate AND o.createdAt <= :endDate")
    List<Order> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByTableNumber(Integer tableNumber);

    @Query("SELECT o FROM Order o WHERE DATE(o.createdAt) = CURRENT_DATE")
    List<Order> findTodayOrders();

    List<Order> findByStatusAndWaiter(OrderStatus status, User waiter);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(OrderStatus status);

    @Query("SELECT SUM(o.total) FROM Order o WHERE o.createdAt >= :startDate AND o.createdAt <= :endDate")
    Double calculateTotalSales(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT o FROM Order o WHERE o.status = :status ORDER BY o.createdAt ASC")
    List<Order> findByStatusOrderByCreatedAtAsc(OrderStatus status);
}
