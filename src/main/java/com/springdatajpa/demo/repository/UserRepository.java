package com.springdatajpa.demo.repository;

import java.util.List;

import com.springdatajpa.demo.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    public User getUserById(Long id);
		public List<User> getAllUser();
		public void deleteUserById(Long id);
		public void editUserInfo(String info);
		UserDetails findByUsername(String userName);
}
