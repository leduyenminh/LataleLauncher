package com.springLataleLauncher.demo.DAO;


import com.springLataleLauncher.demo.entity.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDAO {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
		void deleteUserByUserName(Long userId) throws UsernameNotFoundException;
		void modifyUserInfo(String usernam, String userInfo) throws UsernameNotFoundException;
		List<User> getAllUser();
} 