package com.restaurant.restaurant_api.repositories;

import com.restaurant.restaurant_api.models.InventoryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    // Buscar items con stock por debajo de un umbral
    List<InventoryItem> findByStockLessThan(Double threshold);

    // Buscar items por nombre (ignorando mayúsculas/minúsculas)
    List<InventoryItem> findByNameContainingIgnoreCase(String name);

    // Buscar items por unidad de medida
    List<InventoryItem> findByMeasurementUnitId(Long measurementUnitId);

    // Buscar items con paginación y ordenamiento
    @Query("SELECT i FROM InventoryItem i WHERE " +
            "(:name IS NULL OR LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:measurementUnitId IS NULL OR i.measurementUnit.id = :measurementUnitId) AND " +
            "(:minStock IS NULL OR i.stock >= :minStock) AND " +
            "(:maxStock IS NULL OR i.stock <= :maxStock)")
    Page<InventoryItem> findByFilters(
            @Param("name") String name,
            @Param("measurementUnitId") Long measurementUnitId,
            @Param("minStock") Double minStock,
            @Param("maxStock") Double maxStock,
            Pageable pageable);

    // Verificar si existe un item con el mismo nombre
    boolean existsByNameIgnoreCase(String name);

    // Obtener items que necesitan reposición (stock bajo)
    @Query("SELECT i FROM InventoryItem i WHERE i.stock <= :threshold ORDER BY i.stock ASC")
    List<InventoryItem> findItemsNeedingRestock(@Param("threshold") Double threshold);

    // Obtener items actualizados recientemente
    @Query("SELECT i FROM InventoryItem i ORDER BY i.lastUpdated DESC")
    List<InventoryItem> findRecentlyUpdatedItems(Pageable pageable);
}
