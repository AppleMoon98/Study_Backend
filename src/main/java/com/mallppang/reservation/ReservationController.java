package com.mallppang.reservation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mallppang.member.Member;
import com.mallppang.member.MemberDTO;
import com.mallppang.member.MemberRepository;

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
		List<Reservation> reservations = reservationService.getReservationsByMember(member.getUid());
		List<ReservationDTO> dtos = reservations.stream().map(ReservationMapper::entityToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
	
	@DeleteMapping("/{reservationId}")
	public ResponseEntity<?> cancelReservation(@AuthenticationPrincipal MemberDTO memberDTO, @PathVariable("reservationId") Long reservationId){
		try{
			Member member = memberRepository.findByEmail(memberDTO.getEmail()).orElseThrow();
			reservationService.cancelReservation(reservationId, member.getUid());
			return ResponseEntity.ok().body(Map.of("message", "예약이 성공적으로 취소되었습니다."));
		} catch (IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message",e.getMessage()));
		} catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","서버 오류가 발생했습니다."));
		}
	}
}
