package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.dto.ClientDTO;
import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping

    public Page<Client> getAllClients(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.clientService.getClients(page, size, orderBy);
    }


    @PostMapping
    public Client createClient(@RequestBody ClientDTO client) {
        Client savedClient = clientService.saveClient(client);
        return savedClient;
    }


    @GetMapping("/{id}")
    public Client getClientById(@PathVariable UUID id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);

    }
}
