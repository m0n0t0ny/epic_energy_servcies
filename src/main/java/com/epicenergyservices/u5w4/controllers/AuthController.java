package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.dto.LoginResponseDTO;
import com.epicenergyservices.u5w4.dto.NewUserDTO;
import com.epicenergyservices.u5w4.dto.UserLoginDTO;
import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody UserLoginDTO payload) {
        return new LoginResponseDTO(authService.GenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody NewUserDTO newUser) {

        return this.authService.registerUser(newUser);
    }
}
