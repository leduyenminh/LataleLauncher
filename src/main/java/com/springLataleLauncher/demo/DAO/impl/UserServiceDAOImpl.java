package com.springLataleLauncher.demo.DAO.impl;

import com.springLataleLauncher.demo.entity.User;
import com.springLataleLauncher.demo.repository.UserRepository;

import com.springLataleLauncher.demo.DAO.UserServiceDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

// import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDAOImpl implements UserServiceDAO {

  private final UserRepository userRepository;
	
  @Autowired
  public UserServiceDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

  @PersistenceContext
  private EntityManager em;
  
  @Override
  public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<UserDetails> cq = cb.createQuery(UserDetails.class);
    Root<UserDetails> root = cq.from(UserDetails.class);
    cq.select(root).where(cb.equal(root.get("username"), username));
    return em.createQuery(cq).getSingleResult();
    // return userRepository.findByUsername(username);
  }

  @Override
  public void deleteUserByUserName(Long userId) throws UsernameNotFoundException {
    userRepository.deleteById(userId);
  }

  @Override
  public void modifyUserInfo(String userName,String userInfo) throws UsernameNotFoundException {
    User user = new User();
    user.setUsername(userName);
    user.setUserInfo(userInfo);
    userRepository.save(user);
  }

  @Override
  public List<User> getAllUser() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'getAllUser'");
    return userRepository.findAll();
  }

}
