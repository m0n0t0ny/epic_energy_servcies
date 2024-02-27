package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.payloads.ClientDTO;
import com.epicenergyservices.u5w4.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public Page<Client> getAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String orderBy
    ) {
        return clientService.orderCompanyByName(page, size, orderBy);
    }

    @PostMapping
    public Client saveCustomer(@RequestBody ClientDTO payload) {
        return clientService.save(payload);
    }
}
