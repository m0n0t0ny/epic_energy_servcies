package com.epicenergyservices.u5w4.dao;

import com.epicenergyservices.u5w4.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DAOprovince extends JpaRepository<Province, Long> {
    Optional<Province> findByName(String name);
}
