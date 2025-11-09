package com.restaurant.restaurant_api.repositories;

import com.restaurant.restaurant_api.models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE " +
            "(:name IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:startDate IS NULL OR r.date >= :startDate) AND " +
            "(:endDate IS NULL OR r.date <= :endDate) AND " +
            "(:status IS NULL OR r.status = :status)")
    Page<Reservation> findByFilters(
            @Param("name") String name,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("status") Reservation.ReservationStatus status,
            Pageable pageable);

    @Query("SELECT r FROM Reservation r WHERE r.date = CURRENT_DATE ORDER BY r.time ASC")
    List<Reservation> findTodayReservations();

    @Query(value = "SELECT * FROM reservations ORDER BY created_at DESC LIMIT 100", nativeQuery = true)
    List<Reservation> findRecentReservations(@Param("limit") int limit);
}
