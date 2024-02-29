package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.dto.InvoiceDTO;
import com.epicenergyservices.u5w4.dto.MunicipalityDTO;
import com.epicenergyservices.u5w4.entities.Invoice;
import com.epicenergyservices.u5w4.entities.Municipality;
import com.epicenergyservices.u5w4.entities.User;
import com.epicenergyservices.u5w4.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Invoice> getAllInvoices(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.invoiceService.getInvoices(page, size, orderBy);
    }

    @GetMapping("/me")
    public Page<Invoice> getMyInvoices(@AuthenticationPrincipal User currentAuthenticatedUser,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.invoiceService.getMyInvoices(currentAuthenticatedUser.getId(), page, size, orderBy);
    }

    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Invoice> findByClient(@PathVariable UUID clientId) {
        return invoiceService.findByClient(clientId);
    }

    @GetMapping("/date")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Invoice> findByDate(@RequestParam LocalDate date) {
        return invoiceService.findByDate(date);
    }

    @GetMapping("/amount")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Invoice> findByAmount(@RequestParam double minAmount, @RequestParam double maxAmount) {
        return invoiceService.findByAmount(minAmount, maxAmount);
    }

    @GetMapping("/stato")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Invoice> findByStato(@RequestParam String status) {
        return invoiceService.findByStatus(status);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Invoice findById(@PathVariable UUID id) {
        return this.invoiceService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice saveNewInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return this.invoiceService.saveInvoice(invoiceDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Invoice findByIdAndUpdate(@PathVariable UUID id, @RequestBody Invoice updatedInvoice) {

        return this.invoiceService.findAndUpdate(id, updatedInvoice);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        this.invoiceService.deleteInvoice(id);
    }
}
