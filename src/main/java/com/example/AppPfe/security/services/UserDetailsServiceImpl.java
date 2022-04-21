package com.example.AppPfe.security.services;

import com.example.AppPfe.Models.Authentification;
import com.example.AppPfe.repository.RegistrationAuthtificationRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  RegistrationAuthtificationRepositories userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Authentification user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

}
