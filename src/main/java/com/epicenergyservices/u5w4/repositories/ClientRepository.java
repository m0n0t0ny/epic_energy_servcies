package com.epicenergyservices.u5w4.repositories;

import com.epicenergyservices.u5w4.entities.Address;
import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Client findClientByUserId(UUID user);
    Page<Client> findByAnnualRevenue(Double annualRevenue, Pageable pageable);
    Page<Client> findByCompanyName(String companyName, Pageable pageable);
    Page<Client> findByLastContactDate(LocalDate lastContactDate,  Pageable pageable);
    Page<Client> findByInsertionDate(LocalDate insertionDate, Pageable pageable);
    @Query("SELECT c FROM Client c WHERE c.contactFirstName LIKE %:contactFirstName%")
    Page<Client> findByPartialName(String contactFirstName, Pageable pageable);
    @Query(value = "SELECT c FROM Client c ORDER BY annualRevenue DESC")
    Page<Client> getClientsAndOrderByAnnualRevenue(Pageable pageable);

}
