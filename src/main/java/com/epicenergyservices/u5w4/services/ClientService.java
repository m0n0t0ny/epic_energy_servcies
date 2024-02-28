package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.dto.ClientDTO;
import com.epicenergyservices.u5w4.entities.Address;
import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.enums.ClientType;
import com.epicenergyservices.u5w4.exceptions.NotFoundException;
import com.epicenergyservices.u5w4.repositories.AddressRepository;
import com.epicenergyservices.u5w4.repositories.ClientRepository;
import com.epicenergyservices.u5w4.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;


    public Page<Client> getClients(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Client saveClient(ClientDTO newClient) {
        //prima di inserirli, vengono convertiti da UUID al tipo della classe richiesto dalla classe client
        ClientType clientType = ClientType.valueOf(newClient.clientType());
        Address legalAddress = addressRepository.findById(newClient.legalAddress()).orElse(null);
        Address companyAddress = addressRepository.findById(newClient.companyAddress()).orElse(null);
        User user = userRepository.findById(newClient.user()).orElse(null);

        return clientRepository.save(
                new Client(newClient.company(), newClient.vatNumber(), newClient.email(), newClient.insertionDate(), newClient.lastContactDate(), newClient.annualRevenue(), newClient.certifiedEmail(), newClient.phoneNumber(), newClient.contactEmail(), newClient.contactFirstName(), newClient.contactLastName(), newClient.contactPhoneNumber(),
                        newClient.companyLogo(), clientType, legalAddress, companyAddress, user)
        );
    }

    public Client findAndUpdate(UUID clientId, Client updateClient){
        Client found=this.getClientById(clientId);
        found.setCompanyName(updateClient.getCompanyName());
        found.setVatNumber(updateClient.getVatNumber());
        found.setEmail(updateClient.getEmail());
        found.setInsertionDate(updateClient.getInsertionDate());
        found.setLastContactDate(updateClient.getLastContactDate());
        found.setAnnualRevenue(updateClient.getAnnualRevenue());
        found.setCertifiedEmail(updateClient.getCertifiedEmail());
        found.setPhoneNumber(updateClient.getPhoneNumber());
        found.setContactEmail(updateClient.getContactEmail());
        found.setContactFirstName(updateClient.getContactFirstName());
        found.setContactLastName(updateClient.getContactLastName());
        found.setContactPhoneNumber(updateClient.getContactPhoneNumber());
        found.setCompanyLogo(updateClient.getCompanyLogo());
        found.setClientType(updateClient.getClientType());
        found.setLegalAddress(updateClient.getLegalAddress());
        found.setCompanyAddress(updateClient.getCompanyAddress());
        found.setUser(updateClient.getUser());
        return clientRepository.save(found);

    }


    public void deleteClient(UUID id) {
        Client client=this.getClientById(id);
        clientRepository.delete(client);
    }
}
