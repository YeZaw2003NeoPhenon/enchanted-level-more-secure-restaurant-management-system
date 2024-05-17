package com.restaurantmanagement_system.service;

import java.util.List;

import com.restaurantmanagement_system.model.Table;

public interface tableService {


	public abstract void updateTableStatus(int tableNumber , boolean occupied );
	public abstract List<Table>getAllTable();
	
}
