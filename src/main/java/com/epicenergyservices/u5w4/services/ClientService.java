package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private  ClientRepository clientRepository;



    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(UUID id) {
        return clientRepository.findById(id);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
