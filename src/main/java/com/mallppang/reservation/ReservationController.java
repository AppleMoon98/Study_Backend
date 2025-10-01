package com.mallppang.reservation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mallppang.member.Member;
import com.mallppang.member.MemberDTO;
import com.mallppang.member.MemberRepository;
import com.mallppang.member.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;
	private final MemberRepository memberRepository;

	@PostMapping
	public ResponseEntity<?> createReservation(@AuthenticationPrincipal MemberDTO memberDTO, @RequestBody ReservationDTO reservationDTO) {
		Member member = memberRepository.findByEmail(memberDTO.getEmail()).orElseThrow();
		//안배웠던 부분(신규 추가)
		if(member.getUid() == null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
		
		Reservation reservation = ReservationMapper.dtoToEntity(reservationDTO);
		reservation.setMemberUid(member.getUid());
		Reservation saved = reservationService.createReservation(reservation);
		
		return ResponseEntity.ok(ReservationMapper.entityToDTO(saved));
	}

	@GetMapping
	public ResponseEntity<?> getReservationsByMember(@AuthenticationPrincipal MemberDTO memberDTO) {
		Member member = memberRepository.findByEmail(memberDTO.getEmail()).orElseThrow();
		if(member.getUid() == null) 
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
		
		List<Reservation> reservations = reservationService.getReservationsByMember(member.getUid());
		List<ReservationDTO> dtos = reservations.stream().map(ReservationMapper::entityToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
}
