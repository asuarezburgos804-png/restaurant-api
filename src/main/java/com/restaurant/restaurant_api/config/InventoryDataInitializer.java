package com.restaurant.restaurant_api.config;

import com.restaurant.restaurant_api.models.MeasurementUnit;
import com.restaurant.restaurant_api.repositories.MeasurementUnitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryDataInitializer {
        private final MeasurementUnitRepository measurementUnitRepository;

        public InventoryDataInitializer(MeasurementUnitRepository measurementUnitRepository) {
                this.measurementUnitRepository = measurementUnitRepository;
        }

        @Bean
        public CommandLineRunner initializeInventoryData() {
                return args -> {
                        if (measurementUnitRepository.count() == 0) {
                                // Unidades de peso
                                measurementUnitRepository.save(MeasurementUnit.builder()
                                                .name("Kilogramos")
                                                .symbol("kg")
                                                .build());

                                measurementUnitRepository.save(MeasurementUnit.builder()
                                                .name("Gramos")
                                                .symbol("g")
                                                .build());

                                // Unidades de volumen
                                measurementUnitRepository.save(MeasurementUnit.builder()
                                                .name("Litros")
                                                .symbol("L")
                                                .build());

                                measurementUnitRepository.save(MeasurementUnit.builder()
                                                .name("Mililitros")
                                                .symbol("ml")
                                                .build());

                                // Unidades de conteo
                                measurementUnitRepository.save(MeasurementUnit.builder()
                                                .name("Unidades")
                                                .symbol("u")
                                                .build());

                                measurementUnitRepository.save(MeasurementUnit.builder()
                                                .name("Docenas")
                                                .symbol("doc")
                                                .build());

                                // Unidades de empaque
                                measurementUnitRepository.save(MeasurementUnit.builder()
                                                .name("Cajas")
                                                .symbol("caja")
                                                .build());

                                measurementUnitRepository.save(MeasurementUnit.builder()
                                                .name("Paquetes")
                                                .symbol("paq")
                                                .build());
                        }
                };
        }
}
