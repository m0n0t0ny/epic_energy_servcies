package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.entities.ClientDTO;
import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.entities.UserDTO;
import com.epicenergyservices.u5w4.repositories.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientDAO clientDAO;
    public List<Client> orderCompanyByName() {
        return clientDAO.findAll(Sort.by(Sort.Direction.ASC));
    }
}
