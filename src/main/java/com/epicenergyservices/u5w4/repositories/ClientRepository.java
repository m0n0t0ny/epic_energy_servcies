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

    List<Client> findAllByOrderByCompanyNameAsc();

    List<Client> findByAnnualRevenue(Double annualRevenue);

    Client findClientByUserId(UUID user);
    List<Client> findByCompanyName(String companyName);
    Optional<Client> findByVatNumber(String vatNumber);
    List<Client> findByOrderByAnnualRevenueDesc();
    List<Client> findByInsertionDateBetween(Date startDate, Date endDate);
    List<Client> findByLastContactDate(LocalDate lastContactDate);
    List<Client> findByInsertionDate(LocalDate insertionDate);



}
