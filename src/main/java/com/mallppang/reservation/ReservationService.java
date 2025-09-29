package com.mallppang.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation){
        if(reservation.getMenu() != null){
            reservation.getMenu().forEach(menu -> menu.setReservation(reservation));
        }
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByMember(Long memberUid){
        return reservationRepository.findByMemberUid(memberUid);
    }
}