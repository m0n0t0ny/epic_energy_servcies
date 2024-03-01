package com.epicenergyservices.u5w4.repositories;

import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, UUID> {
    Page<Invoice> findAllByClientId(UUID clientId, Pageable pageable);
    List<Invoice> findByClientId(UUID clientId);
    Page<Invoice> findByDate(LocalDate date, Pageable pageable);
    Page<Invoice> findByAmountBetween(double minAmount, double maxAmount, Pageable pageable);
    Page<Invoice> findByStatus(String status, Pageable pageable);
}
