package com.epicenergyservices.u5w4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRep extends JpaRepository<com.epicenergyservices.u5w4.entities.Municipality, Long> {
}
