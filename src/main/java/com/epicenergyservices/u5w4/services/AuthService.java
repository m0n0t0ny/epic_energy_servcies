package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.exceptions.UnauthorizedException;
import com.epicenergyservices.u5w4.payloads.UserLoginDTO;
import com.epicenergyservices.u5w4.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {
        User user = usersService.findByEmail(payload.email());
        if (user.getPassword().equals(payload.password())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali sbagliate!");
        }


    }
}