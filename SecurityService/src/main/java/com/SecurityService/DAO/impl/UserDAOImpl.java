package com.SecurityService.DAO.impl;

import com.SecurityService.entity.User;
import com.SecurityService.repository.UserRepository;
import com.SecurityService.DAO.UserDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

// import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl implements UserDAO {

  private final UserRepository userRepository;
	
  @Autowired
  public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

  @PersistenceContext
  private EntityManager em;
  
  @Override
  public Optional<UserDetails> loadUserByUsername (String username) throws UsernameNotFoundException {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<UserDetails> cq = cb.createQuery(UserDetails.class);
    Root<UserDetails> root = cq.from(UserDetails.class);
    cq.select(root).where(cb.equal(root.get("username"), username));
    return Optional.ofNullable(em.createQuery(cq).getSingleResult());
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
  public Optional<List<User>> getAllUser() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'getAllUser'");
    return Optional.ofNullable(userRepository.findAll());
  }

}
