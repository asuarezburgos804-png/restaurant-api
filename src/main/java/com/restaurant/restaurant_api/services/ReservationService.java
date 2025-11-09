package com.restaurant.restaurant_api.services;

import com.restaurant.restaurant_api.dto.reservation.requests.CreateReservationRequest;
import com.restaurant.restaurant_api.dto.reservation.requests.UpdateReservationRequest;
import com.restaurant.restaurant_api.dto.reservation.responses.ReservationDTO;
import com.restaurant.restaurant_api.models.Reservation;
import com.restaurant.restaurant_api.repositories.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private static final Logger log = LoggerFactory.getLogger(ReservationService.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Page<ReservationDTO> getAllReservations(
            String name,
            String startDate,
            String endDate,
            String status,
            Pageable pageable) {

        LocalDate start = startDate != null ? LocalDate.parse(startDate, DATE_FORMATTER) : null;
        LocalDate end = endDate != null ? LocalDate.parse(endDate, DATE_FORMATTER) : null;
        Reservation.ReservationStatus statusEnum = status != null
                ? Reservation.ReservationStatus.valueOf(status.toUpperCase())
                : null;

        return reservationRepository.findByFilters(name, start, end, statusEnum, pageable)
                .map(this::convertToDTO);
    }

    @Transactional
    public ReservationDTO getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
    }

    @Transactional
    public ReservationDTO createReservation(CreateReservationRequest request) {
        Reservation reservation = Reservation.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .date(LocalDate.parse(request.getDate(), DATE_FORMATTER))
                .time(LocalTime.parse(request.getTime(), TIME_FORMATTER))
                .guests(request.getGuests())
                .status(Reservation.ReservationStatus.PENDIENTE)
                .build();

        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToDTO(savedReservation);
    }

    @Transactional
    public ReservationDTO updateReservation(Long id, UpdateReservationRequest request) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));

        if (request.getName() != null) {
            reservation.setName(request.getName());
        }
        if (request.getPhone() != null) {
            reservation.setPhone(request.getPhone());
        }
        if (request.getDate() != null) {
            reservation.setDate(LocalDate.parse(request.getDate(), DATE_FORMATTER));
        }
        if (request.getTime() != null) {
            reservation.setTime(LocalTime.parse(request.getTime(), TIME_FORMATTER));
        }
        if (request.getGuests() != null) {
            reservation.setGuests(request.getGuests());
        }
        if (request.getStatus() != null) {
            reservation.setStatus(Reservation.ReservationStatus.valueOf(request.getStatus().toUpperCase()));
        }

        Reservation updatedReservation = reservationRepository.save(reservation);
        return convertToDTO(updatedReservation);
    }

    @Transactional
    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new EntityNotFoundException("Reserva no encontrada");
        }
        reservationRepository.deleteById(id);
    }

    @Transactional
    public List<ReservationDTO> getTodayReservations() {
        return reservationRepository.findTodayReservations()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ReservationDTO> getRecentReservations(int limit) {
        return reservationRepository.findRecentReservations(limit)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .name(reservation.getName())
                .phone(reservation.getPhone())
                .date(reservation.getDate().format(DATE_FORMATTER))
                .time(reservation.getTime().format(TIME_FORMATTER))
                .guests(reservation.getGuests())
                .status(reservation.getStatus())
                .createdAt(reservation.getCreatedAt().toString())
                .updatedAt(reservation.getUpdatedAt().toString())
                .build();
    }
}
