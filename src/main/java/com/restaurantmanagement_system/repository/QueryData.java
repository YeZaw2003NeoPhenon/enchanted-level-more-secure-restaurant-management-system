package com.restaurantmanagement_system.repository;

import lombok.Getter;

public class QueryData {
	@Getter
	private static final String SELECT_ALL = "SELECT orderId, tableNumber , itemName , quantity, totalPrices FROM \"Order\" ";
	
	
	@Getter
	private static final String CREATE_ORDER =  "INSERT INTO \"Order\"( tableNumber , itemName , quantity, totalPrices) VALUES (? , ? , ? , ?)";
	
	@Getter
	private static final String DELETE_ORDER = "DELETE FROM \"Order\" WHERE orderId = ?";
	
	@Getter
	private static final String UPDATE_ORDER = "UPDATE \"Order\" SET tableNumber =?, itemName = ?, quantity = ?, totalPrices =? WHERE orderId = ?";
	
	@Getter
	private static final String GET_ORDER_BY_ID = "SELECT orderId, tableNumber , itemName , quantity, totalPrices FROM \"Order\" WHERE orderId =?";
	
	@Getter
	private static final String GET_TABLE_DATAS = "SELECT * FROM  \"Table\" ";
	
	@Getter
	private static final String UPDATE_TABLE_STAUS = "UPDATE \"Table\" SET occupied = ? WHERE tableNumber = ?";
	
	@Getter
	private static final String GET_ORDER_TABLE = "SELECT orderId, tableNumber , itemName , quantity, totalPrices FROM \"Order\" WHERE tableNumber = ?";
	
	@Getter
	private static final String ACCOUNT_LOGIN_PROCESS = "SELECT users.username , users.password , users.enabled , authorities.authority FROM \"users\"  INNER JOIN \"authorities\" ON users.username = authorities.username WHERE users.username = ?;";
}
