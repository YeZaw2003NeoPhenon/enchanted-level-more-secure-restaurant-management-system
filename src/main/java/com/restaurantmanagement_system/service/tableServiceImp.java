package com.restaurantmanagement_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantmanagement_system.model.Table;
import com.restaurantmanagement_system.repository.TableRepository;

@Service
public class tableServiceImp implements tableService{
	@Autowired
	private TableRepository tableRepository;

	
	@Override
	public List<Table> getAllTable() {
		return tableRepository.getAllTables();
	}
	
	@Override
	public void updateTableStatus(int tableNumber, boolean occupied) {
		tableRepository.updateTableStatus(tableNumber, occupied);
	}
	
}
