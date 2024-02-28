package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.dto.NewUserDTO;
import com.epicenergyservices.u5w4.dto.UserLoginDTO;
import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.exceptions.BadRequestException;
import com.epicenergyservices.u5w4.exceptions.UnauthorizedException;
import com.epicenergyservices.u5w4.repositories.UserRepository;
import com.epicenergyservices.u5w4.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTTools jwtTools;

    public String GenerateToken(UserLoginDTO payload) {
        User user = usersService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali sbagliate!");
        }
    }

    public User registerUser(NewUserDTO payload) {
        userRepository.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });
        User newUser = new User(payload.username(), payload.email(), bcrypt.encode(payload.password()), payload.name(), payload.surname(), payload.avatar());

        return userRepository.save(newUser);
    }

//    public User saveUser(NewUserDTO payload) {
//        usersDAO.findByEmail(payload.email()).ifPresent(user -> {
//            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
//        });
//
//        User newUser = new User(payload.name(), payload.surname(),
//                payload.email(), bcrypt.encode(payload.password()));
//        return usersDAO.save(newUser);
//    }


}
