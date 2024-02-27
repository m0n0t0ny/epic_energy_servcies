package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.exceptions.UserNotFoundException;
import com.epicenergyservices.u5w4.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final PasswordEncoder passwordEncoder;


  public User registerUser(User user) {
    Optional<User> existingUserOptional = userRepository.findByUsername(user.getUsername());
    if (existingUserOptional.isPresent()) {
      throw new RuntimeException("User already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User findById(UUID userId) {
    return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
  }

  public User findByUsername(String username) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    return userOptional.orElse(null);
  }
}
