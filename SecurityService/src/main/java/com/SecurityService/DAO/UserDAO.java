package com.SecurityService.DAO;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.SecurityService.entity.User;

public interface UserDAO {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	void deleteUserByUserName(Long userId) throws UsernameNotFoundException;
	void modifyUserInfo(String username, String userInfo) throws UsernameNotFoundException;
	List<User> getAllUser();
} 