package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.dto.AddressDTO;
import com.epicenergyservices.u5w4.dto.InvoiceDTO;
import com.epicenergyservices.u5w4.entities.Address;
import com.epicenergyservices.u5w4.entities.Invoice;
import com.epicenergyservices.u5w4.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping
    public Page<Address> getAllAddresss(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.addressService.getAddress(page, size, orderBy);
    }


    @GetMapping("/{id}")
    public Address findById(@PathVariable UUID id) {
        return this.addressService.getAddressById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Address saveNewInvoice(@RequestBody AddressDTO  addressDTO){
        return this.addressService.saveAddress(addressDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Address findByIdAndUpdate(@PathVariable UUID id, @RequestBody Address updatedAddress) {

        return this.addressService.findAndUpdate(id, updatedAddress);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        this.addressService.deleteAddress(id);
    }

}
