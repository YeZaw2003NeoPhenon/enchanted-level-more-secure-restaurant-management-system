package com.restaurantmanagement_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantmanagement_system.model.Order;
import com.restaurantmanagement_system.repository.orderRepository;

@Service
public class orderServiceImp implements orderService {

	@Autowired
	private orderRepository orderRespotory;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRespotory.getAllOrders();
	}

	@Override
	public int createOrder(Order order) {
		return orderRespotory.addOrder(order);
	}

	@Override
	public int deleteOrder(int orderId) {
		return orderRespotory.deleteOrder(orderId);
	}

	@Override
	public Order getOrderById(int orderId) {
		return orderRespotory.getOrderById(orderId);
	}

	@Override
	public int updateOrder(Order order) {
		return orderRespotory.updateOrder(order);
	}

	@Override
	public List<Order> getOrdersFromTable(int tableNumber) {
		return orderRespotory.getOrdersFromTable(tableNumber);
	}


}
