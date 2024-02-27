package com.epicenergyservices.u5w4.services;


import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.payloads.ClientDTO;
import com.epicenergyservices.u5w4.repositories.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientDAO clientDAO;

    public Client save(ClientDTO payload) {
        Client client = new Client(payload.id, payload.company_name, p)
        return clientDAO.save(client);
    }

    public Page<Client> orderCompanyByName(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.ASC, "companyName"));
        return clientDAO.findAll(pageable);
    }

}
