package com.SecurityService.DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.SecurityService.entity.User;

public interface UserDAO {
    Optional<UserDetails> loadUserByUsername(String username) throws UsernameNotFoundException;
	void deleteUserByUserName(Long userId) throws UsernameNotFoundException;
	void modifyUserInfo(String username, String userInfo) throws UsernameNotFoundException;
	Optional<List<User>> getAllUser();
} 