package com.mallppang.reservation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mallppang.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	@PostMapping
	public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) {
		Reservation reservation = ReservationMapper.toEntity(reservationDTO);
		Reservation saved = reservationService.createReservation(reservation);
		return ResponseEntity.ok(ReservationMapper.toDTO(saved));
	}

	@GetMapping
	public ResponseEntity<?> getReservationsByMember() {
		Long memberUid = 1L;
		List<Reservation> reservations = reservationService.getReservationsByMember(memberUid);
		List<ReservationDTO> dtos = reservations.stream().map(ReservationMapper::toDTO).collect(Collectors.toList());

		return ResponseEntity.ok(dtos);
	}
}
