package com.restaurantmanagement_system.model;


import lombok.Getter;
import lombok.Setter;

public class Order {
	
	@Getter
	@Setter
	private int orderId ;
	
	@Getter
	@Setter
	private int tableNumber;
	
	@Getter
	@Setter
	private String itemName;
	
	@Getter
	@Setter
	private int quantity;
	
	@Getter
	@Setter
	private double totalPrices;
	
	public Order() {}
	
	public Order( int orderId, int tableNumber , String itemName , int quantity , double totalPrices) {
		this.orderId = orderId;
		this.tableNumber = tableNumber;
		this.itemName = itemName;
		this.quantity = quantity;
		this.totalPrices = totalPrices;
	}
}