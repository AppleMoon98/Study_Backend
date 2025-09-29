package com.mallppang.reservation;

import java.util.stream.Collectors;

public class ReservationMapper {
	
	public static Reservation toEntity(ReservationDTO dto){
		Reservation reservation = Reservation.builder()
						.bakeryId(dto.getBakeryId())
						.bakeryName(dto.getBakeryName())
						.bakeryAddress(dto.getBakeryAddress())
						.date(dto.getDate())
						.time(dto.getTime())
						.totalPrice(dto.getTotalPrice())
						.menu(dto.getMenu() == null ? null : dto.getMenu().stream()
								.map(menuDTO -> MenuItem.builder()
										.menuId(menuDTO.getMenuId())
										.name(menuDTO.getName())
										.quantity(menuDTO.getQuantity())
										.price(menuDTO.getPrice())
										.totalPrice(menuDTO.getTotalPrice())
										.build()
								).collect(Collectors.toList()))
						.build();
						
		  if(reservation.getMenu() != null){
			  reservation.getMenu().forEach(menu -> menu.setReservation(reservation));
		  }
		  
		  return reservation;
	}
	
	public static ReservationDTO toDTO(Reservation reservation){
		ReservationDTO dto = new ReservationDTO();
		dto.setBakeryId(reservation.getBakeryId());
		dto.setBakeryName(reservation.getBakeryName());
		dto.setBakeryAddress(reservation.getBakeryAddress());
		dto.setDate(reservation.getDate());
		dto.setTotalPrice(reservation.getTotalPrice());
		
		if(reservation.getMenu() !=null){
			dto.setMenu(reservation.getMenu().stream().map(menu -> new ReservationDTO.MenuItemDTO(
				menu.getMenuId(),
				menu.getName(),
				menu.getQuantity(),
				menu.getPrice(),
				menu.getTotalPrice()
			)).collect(Collectors.toList()));
		}
		return dto;
	}
}

