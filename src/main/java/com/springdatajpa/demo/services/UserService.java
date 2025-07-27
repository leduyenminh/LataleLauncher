package com.springdatajpa.demo.services;


import com.springdatajpa.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
		void deleteUserByUserName(String username)throws UsernameNotFoundException;
		void modifyUserInfo(String usernam, String userInfo) throws UsernameNotFoundException;
} 