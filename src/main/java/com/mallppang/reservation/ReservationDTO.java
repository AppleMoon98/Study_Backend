package com.mallppang.reservation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
	private Long bakeryId;
	private String bakeryName;
	private String bakeryAddress;
	private String date;
	private String time;
	private int totalPrice;
	private List<MenuItemDTO> menu;
	
	@Getter
	@Setter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MenuItemDTO{
		private Long menuId;
		private String name;
		private int quantity;
		private int price;
		private int totalPrice;
	}
}
