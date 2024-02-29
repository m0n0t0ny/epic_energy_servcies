package com.epicenergyservices.u5w4.controllers;



import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy
  ) {
    return this.userService.getUsers(page, size, orderBy);
  }

  @GetMapping("/me")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
    return currentAuthenticatedUser;
  }



  @PutMapping("/me")
  public User getMeAndUpdate(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody User updatedUser) {
    return this.userService.findByIdAndUpdate(currentAuthenticatedUser.getId(), updatedUser);
  }

  @DeleteMapping("/me")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void getMeAndDelete(@AuthenticationPrincipal User currentAuthenticatedUser) {
    this.userService.findByIdAndDelete(currentAuthenticatedUser.getId());
  }


  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public User findById(@PathVariable UUID id) {
    return this.userService.findById(id);
  }


  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public User findByIdAndUpdate(@PathVariable UUID id, @RequestBody User updatedUser) {

    return this.userService.findByIdAndUpdate(id, updatedUser);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void findByIdAndDelete(@PathVariable UUID id) {
    this.userService.findByIdAndDelete(id);
  }

  
}
