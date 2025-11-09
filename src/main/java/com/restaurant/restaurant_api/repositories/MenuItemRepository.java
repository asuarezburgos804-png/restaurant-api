package com.restaurant.restaurant_api.repositories;

import com.restaurant.restaurant_api.models.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("SELECT m FROM MenuItem m WHERE " +
            "(:name IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:categoryId IS NULL OR m.category.id = :categoryId) AND " +
            "(:available IS NULL OR m.available = :available)")
    Page<MenuItem> findByFilters(
            @Param("name") String name,
            @Param("categoryId") Long categoryId,
            @Param("available") Boolean available,
            Pageable pageable);

    List<MenuItem> findByCategoryId(Long categoryId);

    List<MenuItem> findByAvailableTrue();

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    @Query("SELECT m FROM MenuItem m ORDER BY m.updatedAt DESC")
    List<MenuItem> findRecentlyUpdated(Pageable pageable);
}
