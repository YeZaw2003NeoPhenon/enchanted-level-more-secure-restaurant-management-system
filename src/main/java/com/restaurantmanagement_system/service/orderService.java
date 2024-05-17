package com.restaurantmanagement_system.service;

import java.util.List;

import com.restaurantmanagement_system.model.Order;

public interface orderService {

	public abstract List<Order>getAllOrders();
	public abstract int createOrder(Order order );
	public abstract int deleteOrder(int orderId );
	public abstract Order getOrderById(int orderId);
	public abstract int updateOrder(Order order );
	public abstract List<Order> getOrdersFromTable(int tableNumber );
}
