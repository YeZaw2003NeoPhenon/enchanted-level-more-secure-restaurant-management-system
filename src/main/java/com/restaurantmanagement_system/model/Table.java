package com.restaurantmanagement_system.model;

import lombok.Getter;
import lombok.Setter;

public class Table {
	
	@Getter
	@Setter
	private int tableNumber;
	
	@Getter
	@Setter
	private boolean occupied;
	
	public Table() {}
	public Table( int tableNumber , boolean occupied) {
		this.tableNumber = tableNumber;
		this.occupied = occupied;
	}
}
