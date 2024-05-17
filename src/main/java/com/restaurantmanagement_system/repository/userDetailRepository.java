package com.restaurantmanagement_system.repository;

import com.restaurantmanagement_system.model.userDetailModel;

public interface userDetailRepository {
	 public abstract userDetailModel loginProcess( String username );
}
