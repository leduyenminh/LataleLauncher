package com.springdatajpa.demo.services.impl;

import com.springdatajpa.demo.entity.User;
import com.springdatajpa.demo.repository.UserRepository;

import com.springdatajpa.demo.services.UserService;

import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	@Override
	public void deleteUserByUserName(String username) throws UsernameNotFoundException {

	}

	@Override
	public void modifyUserInfo(String userName,String userInfo) throws UsernameNotFoundException {

	}

}
