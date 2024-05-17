package com.restaurantmanagement_system.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restaurantmanagement_system.model.userDetailModel;
import com.restaurantmanagement_system.repository.userDetailRepository;

@Service
public class userDetailServiceImp implements UserDetailsService{
	
	@Autowired
	private userDetailRepository userDetailRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 userDetailModel userDetailModel = userDetailRepository.loginProcess(username);
		return new User(userDetailModel.getUsername(), userDetailModel.getPassword() , new ArrayList<>());
	}
	
}
