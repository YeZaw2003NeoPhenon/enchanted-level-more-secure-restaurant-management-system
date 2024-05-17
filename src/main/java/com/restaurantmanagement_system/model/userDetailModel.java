package com.restaurantmanagement_system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class userDetailModel {

	private String username;
	
	private String password;
	
	private boolean enabled;
	
	private String authority;
	
}
