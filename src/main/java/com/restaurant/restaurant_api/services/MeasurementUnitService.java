package com.restaurant.restaurant_api.services;

import com.restaurant.restaurant_api.dto.inventory.responses.MeasurementUnitDTO;
import com.restaurant.restaurant_api.models.MeasurementUnit;
import com.restaurant.restaurant_api.repositories.MeasurementUnitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementUnitService {
    private final MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    public MeasurementUnitService(MeasurementUnitRepository measurementUnitRepository) {
        this.measurementUnitRepository = measurementUnitRepository;
    }

    @Transactional(readOnly = true)
    public List<MeasurementUnitDTO> getAllUnits() {
        return measurementUnitRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MeasurementUnitDTO getUnitById(Long id) {
        return measurementUnitRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Measurement unit not found"));
    }

    @Transactional
    public MeasurementUnitDTO createUnit(MeasurementUnitDTO dto) {
        if (measurementUnitRepository.existsByNameOrSymbol(dto.getName(), dto.getSymbol())) {
            throw new IllegalArgumentException("A unit with this name or symbol already exists");
        }

        MeasurementUnit unit = MeasurementUnit.builder()
                .name(dto.getName())
                .symbol(dto.getSymbol())
                .build();

        return convertToDTO(measurementUnitRepository.save(unit));
    }

    @Transactional
    public MeasurementUnitDTO updateUnit(Long id, MeasurementUnitDTO dto) {
        MeasurementUnit unit = measurementUnitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Measurement unit not found"));

        if (!unit.getName().equals(dto.getName()) || !unit.getSymbol().equals(dto.getSymbol())) {
            if (measurementUnitRepository.existsByNameOrSymbol(dto.getName(), dto.getSymbol())) {
                throw new IllegalArgumentException("A unit with this name or symbol already exists");
            }
        }

        unit.setName(dto.getName());
        unit.setSymbol(dto.getSymbol());

        return convertToDTO(measurementUnitRepository.save(unit));
    }

    @Transactional
    public void deleteUnit(Long id) {
        if (!measurementUnitRepository.existsById(id)) {
            throw new EntityNotFoundException("Measurement unit not found");
        }
        measurementUnitRepository.deleteById(id);
    }

    private MeasurementUnitDTO convertToDTO(MeasurementUnit unit) {
        return MeasurementUnitDTO.builder()
                .id(unit.getId())
                .name(unit.getName())
                .symbol(unit.getSymbol())
                .build();
    }
}
