package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.dto.ClientDTO;
import com.epicenergyservices.u5w4.dto.InvoiceDTO;
import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.entities.Invoice;
import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.services.ClientService;
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
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Client> getAllClients(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.clientService.getClients(page, size, orderBy);
    }

    @GetMapping("/me")
    public Client getMeClient(@AuthenticationPrincipal User currentAuthenticatedUser){
        return clientService.getMySelf(currentAuthenticatedUser.getId());
    }


    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody ClientDTO client) {
        Client savedClient = clientService.saveClient(client);
        return savedClient;
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client getClientById(@PathVariable UUID id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client findByIdAndUpdate(@PathVariable UUID id, @RequestBody Client updatedClient) {

        return this.clientService.findAndUpdate(id, updatedClient);
    }
}
