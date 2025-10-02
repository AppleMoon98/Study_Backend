package com.mallppang.reservation;

import java.util.stream.Collectors;

public class ReservationMapper {
	
	public static Reservation dtoToEntity(ReservationDTO dto){
		Reservation entity = Reservation.builder()
						.bakeryId(dto.getBakeryId())
						.bakeryName(dto.getBakeryName())
						.bakeryAddress(dto.getBakeryAddress())
						.date(dto.getDate())
						.time(dto.getTime())
						.totalPrice(dto.getTotalPrice())
						.delFlag(dto.isDelFlag())
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
						
		  if(entity.getMenu() != null)
			  entity.getMenu().forEach(menu -> menu.setReservation(entity));
		  
		  return entity;
	}
	
	public static ReservationDTO entityToDTO(Reservation entity){
		ReservationDTO dto = ReservationDTO.builder()
				.id(entity.getId())
				.bakeryId(entity.getBakeryId())
				.bakeryName(entity.getBakeryName())
				.bakeryAddress(entity.getBakeryAddress())
				.date(entity.getDate())
				.time(entity.getTime())
				.totalPrice(entity.getTotalPrice())
				.delFlag(entity.getDelFlag())
				.build();
		
		if(entity.getMenu() !=null){
			dto.setMenu(entity.getMenu().stream().map(menu -> new ReservationDTO.MenuItemDTO(
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

