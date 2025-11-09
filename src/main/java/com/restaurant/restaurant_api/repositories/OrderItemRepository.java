package com.restaurant.restaurant_api.repositories;

import com.restaurant.restaurant_api.models.Order;
import com.restaurant.restaurant_api.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.createdAt >= :startDate AND oi.order.createdAt <= :endDate")
    List<OrderItem> findByOrderDateRange(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT oi.item.id, oi.item.name, SUM(oi.quantity) as totalQuantity " +
            "FROM OrderItem oi " +
            "WHERE oi.order.createdAt >= :startDate AND oi.order.createdAt <= :endDate " +
            "GROUP BY oi.item.id, oi.item.name " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findMostOrderedItems(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi WHERE oi.item.id = :itemId")
    Long countTotalOrderedByItemId(Long itemId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.status = 'PENDING' ORDER BY oi.order.createdAt ASC")
    List<OrderItem> findPendingOrderItems();
}
