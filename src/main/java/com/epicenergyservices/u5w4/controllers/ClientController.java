package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

  private final ClientService clientService;

  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping
  public ResponseEntity<Client> createClient(@RequestBody Client client) {
    Client savedClient = clientService.saveClient(client);
    return ResponseEntity.ok(savedClient);
  }

  @GetMapping
  public ResponseEntity<List<Client>> getAllClients() {
    List<Client> clients = clientService.getAllClients();
    return ResponseEntity.ok(clients);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Client> getClientById(@PathVariable UUID id) {
    return clientService.getClientById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
    clientService.deleteClient(id);
    return ResponseEntity.ok().build();
  }
}
