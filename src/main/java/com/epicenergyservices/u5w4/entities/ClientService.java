package com.epicenergyservices.u5w4.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    public void salvaCliente(){
        ClientDTO cliente = new ClientDTO(0,"prova",1,"prova@prova.it",27/02/20024,
                2000,100,"certified@prova.it","+39 00000000",
                "contact@prova.it","firtName","lastName","+3900",
                "logoCompany","Admin",1,"1",1,1);
       ClientDTO clientesalvato = clientRepository.save(cliente);
    }
}