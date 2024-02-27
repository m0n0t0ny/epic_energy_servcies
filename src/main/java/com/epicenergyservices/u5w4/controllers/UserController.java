package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.exceptions.UserNotFoundException;
import com.epicenergyservices.u5w4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable UUID id) {
    try {
      User user = userService.findById(id);
      return ResponseEntity.ok(user);
    } catch (UserNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
