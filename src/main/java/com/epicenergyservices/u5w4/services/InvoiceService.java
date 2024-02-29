package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.dto.InvoiceDTO;
import com.epicenergyservices.u5w4.entities.Invoice;
import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.exceptions.NotFoundException;
import com.epicenergyservices.u5w4.repositories.ClientRepository;
import com.epicenergyservices.u5w4.repositories.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepo invoiceRepo;
    @Autowired
    private ClientRepository clientRepository;

    public Page<Invoice> getInvoices(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return invoiceRepo.findAll(pageable);
    }

    public Page<Invoice> getMyInvoices(UUID currentUser, int pageNumber, int size, String orderBy) {
        Client client = clientRepository.findClientByUserId(currentUser);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        Page<Invoice> clientInvoices = invoiceRepo.findAllByClientId(client.getId(), pageable);
        if (clientInvoices.isEmpty()) {
            throw new NotFoundException("Non ci sono fatture registrate con questo id cliente");
        }
        return clientInvoices;
    }

    public Invoice findById(UUID id) {
        return invoiceRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Invoice saveInvoice(InvoiceDTO newInvoice) {
        Client client = clientRepository.findById(newInvoice.client()).orElse(null);
        return invoiceRepo.save(
                new Invoice(newInvoice.date(), newInvoice.amount(), newInvoice.status(), client)
        );
    }

    public Invoice findAndUpdate(UUID invoiceId, Invoice updateInvoice) {
        Invoice found = this.findById(invoiceId);
        found.setDate(updateInvoice.getDate());
        found.setAmount(updateInvoice.getAmount());
        found.setStatus(updateInvoice.getStatus());
        found.setClient(updateInvoice.getClient());
        return invoiceRepo.save(found);
    }

    public void deleteInvoice(UUID id) {
        Invoice invoice = this.findById(id);
        invoiceRepo.delete(invoice);
    }
}
