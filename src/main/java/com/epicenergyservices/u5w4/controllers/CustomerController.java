package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.entities.Customer;
import com.epicenergyservices.u5w4.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public Page<Customer> getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String orderBy
    ) {
        return clientService.orderCompanyByName(page, size, orderBy);
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return clientService.save(customer);
    }
}
