package com.epicenergyservices.u5w4.repositories;

import com.epicenergyservices.u5w4.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

  List<Client> findAllByOrderByCompanyNameAsc();

  List<Client> findByAnnualRevenue(Double annualRevenue);

  List<Client> findByCompanyNameContaining(String companyNamePart);
}
