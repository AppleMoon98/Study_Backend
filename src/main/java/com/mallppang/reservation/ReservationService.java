package com.mallppang.reservation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

	private final ReservationRepository reservationRepository;

	public Reservation createReservation(Reservation reservation) {
		if (reservation.getMenu() != null) {
			reservation.getMenu().forEach(menu -> menu.setReservation(reservation));
		}
		return reservationRepository.save(reservation);
	}

	public void cancelReservation(Long reservationId, Long memberUid) {
		Reservation reservation = reservationRepository.findById(reservationId)
				.orElseThrow(() -> new IllegalArgumentException("예약 정보를 찾을 수 없습니다."));

		if (!reservation.getMemberUid().equals(memberUid)) {
			throw new IllegalArgumentException("해당 예약을 취소할 권한이 없습니다.");
		}
//    	이거 말고 delFlag 수정하고 저장하면됨
//      reservationRepository.deleteById(reservationId);
		
		reservation.setDelFlag(true);
		reservationRepository.save(reservation);
	}

	public List<Reservation> getReservationsByMember(Long memberUid) {
		return reservationRepository.findByMemberUid(memberUid);
	}
}