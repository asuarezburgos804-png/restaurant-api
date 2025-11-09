package com.restaurant.restaurant_api.repositories;

import com.restaurant.restaurant_api.models.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Long> {
    Optional<MeasurementUnit> findByName(String name);

    Optional<MeasurementUnit> findBySymbol(String symbol);

    boolean existsByNameOrSymbol(String name, String symbol);
}
