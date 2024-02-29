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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;


    public Page<Client> getClients(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return clientRepository.findAll(pageable);
    }
    public Client getMySelf(UUID userId){
        Client client=clientRepository.findClientByUserId(userId);
        if (client==null){
            throw new NotFoundException("nessun cliente collegato");
        }
        return client;
    }

    public Client getClientById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Client saveClient(ClientDTO newClient) {
        //prima di inserirli, vengono convertiti da UUID al tipo della classe richiesto dalla classe client
        ClientType clientType = ClientType.valueOf(newClient.clientType());
        Address legalAddress = addressService.getAddressById(newClient.legalAddress());
        Address companyAddress = addressService.getAddressById(newClient.companyAddress());
        User user = userService.findById(newClient.user());

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

    public List<Client> findByCompanyName(String companyName){
        List<Client> clients=clientRepository.findByCompanyName(companyName);
        if (clients.isEmpty()){
            throw new NotFoundException("nessun client associato a "+companyName);
        }
        return clients;
    }
    public List<Client> findByRevenue(double annualRevenue){
        List<Client> clients=clientRepository.findByAnnualRevenue(annualRevenue);
        if (clients.isEmpty()){
            throw new NotFoundException("nessun client trovato con il revenue specificato");
        }
        return clients;
    }
    public List<Client> findByLastContact(LocalDate lastaContactDate){
        List<Client> clients=clientRepository.findByLastContactDate(lastaContactDate);
        if (clients.isEmpty()){
            throw new NotFoundException("nessun client trovato con la data specificata");
        }
        return clients;
    }
    public List<Client> findByInsertionDate(LocalDate insertionDate){
        List<Client> clients=clientRepository.findByInsertionDate(insertionDate);
        if (clients.isEmpty()){
            throw new NotFoundException("nessun client trovato con la data specificata");
        }
        return clients;
    }

    public List<Client> findByPartialname(String contactFirstName) {
        List<Client> clients = clientRepository.findByPartialName(contactFirstName);
        if (clients.isEmpty()) {
            throw new NotFoundException("nessun client trovato");
        }
        return clients;
    }

}
