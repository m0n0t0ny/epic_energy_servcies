package com.epicenergyservices.u5w4.repositories;

import com.epicenergyservices.u5w4.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, UUID> {
}
