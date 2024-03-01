package com.epicenergyservices.u5w4.repositories;

import com.epicenergyservices.u5w4.entities.Address;
import com.epicenergyservices.u5w4.entities.Client;
import com.epicenergyservices.u5w4.entities.Invoice;
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
    List<Client> findByAnnualRevenue(Double annualRevenue);
    List<Client> findByCompanyName(String companyName);
    List<Client> findByLastContactDate(LocalDate lastContactDate);
    List<Client> findByInsertionDate(LocalDate insertionDate);
    @Query("SELECT c FROM Client c WHERE c.contactFirstName LIKE %:contactFirstName%")
    List<Client> findByPartialName(String contactFirstName);


}
