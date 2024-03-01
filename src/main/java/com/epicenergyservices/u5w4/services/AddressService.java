package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.dto.AddressDTO;
import com.epicenergyservices.u5w4.entities.*;
import com.epicenergyservices.u5w4.entities.Address;
import com.epicenergyservices.u5w4.enums.AddressType;
import com.epicenergyservices.u5w4.exceptions.NotFoundException;
import com.epicenergyservices.u5w4.repositories.AddressRepository;
import com.epicenergyservices.u5w4.repositories.ClientRepository;
import com.epicenergyservices.u5w4.repositories.MunicipalityRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MunicipalityRep municipalityRep;
    @Autowired
    private ClientRepository clientRepository;

    public Page<Address> getAddress(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return addressRepository.findAll(pageable);
    }

    public List<Address> getMyAddress(UUID userId){
        Client client= clientRepository.findClientByUserId(userId);
        Address legal=client.getLegalAddress();
        Address company=client.getCompanyAddress();
        List<Address> clientAddress=new ArrayList<>();
        clientAddress.add(legal);
        clientAddress.add(company);
        return clientAddress;
    }

    public Address getAddressById(UUID id) {
        return addressRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Address saveAddress(AddressDTO newAddress) {
        Municipality municipality = municipalityRep.findById(newAddress.municipality()).orElse(null);
        AddressType addressType = AddressType.valueOf(newAddress.type());
        return addressRepository.save(
                new Address(newAddress.street(), newAddress.civicNumber(), newAddress.location(), newAddress.postalCode(), municipality, addressType)
        );
    }
    public Address findAndUpdate(UUID addressId, Address updateAddress) {
        Address found = this.getAddressById(addressId);
        found.setStreet(updateAddress.getStreet());
        found.setCivicNumber(updateAddress.getCivicNumber());
        found.setLocation(updateAddress.getLocation());
        found.setPostalCode(updateAddress.getPostalCode());
        found.setMunicipality(updateAddress.getMunicipality());
        found.setType(updateAddress.getType());
        return addressRepository.save(found);
    }
    public void deleteAddress(UUID id) {
        Address address=this.getAddressById(id);
        addressRepository.delete(address);
    }
}
