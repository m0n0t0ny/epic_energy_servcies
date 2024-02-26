package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.entities.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDTO userDTO;
    public List<User> orderUserByName() {
        return
    }
}
