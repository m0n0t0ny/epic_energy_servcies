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
        Client client = new Client(payload.id, payload.company_name, payload.vat_number, payload.email, payload.insertion_date, payload.last_contact_date, payload.annual_revenue, payload.certified_email, payload.phone_number, payload.contact_email, payload.contact_first_name, payload.contact_last_name, payload.contact_phone_number, payload.company_logo, payload.legal_address_id, payload.company_address_id, payload.invoices_id, payload.user_id);
        return clientDAO.save(client);
    }

    public Page<Client> orderCompanyByName(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.ASC, "companyName"));
        return clientDAO.findAll(pageable);
    }

}
