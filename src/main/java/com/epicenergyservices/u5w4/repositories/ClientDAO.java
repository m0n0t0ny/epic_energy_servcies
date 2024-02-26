package com.epicenergyservices.u5w4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClientDAO extends JpaRepository<Client, UUID> {
    public Client orderCompanyByName();
}
