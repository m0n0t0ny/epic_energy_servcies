package com.epicenergyservices.u5w4.repositories;

import com.epicenergyservices.u5w4.controllers.MunicipalityController;
import com.epicenergyservices.u5w4.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRep extends JpaRepository<com.epicenergyservices.u5w4.entities.Municipality, Long> {
    Municipality findByName(String name);
}
