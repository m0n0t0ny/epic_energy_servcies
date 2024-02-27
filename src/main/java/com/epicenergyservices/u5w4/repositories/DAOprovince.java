package com.epicenergyservices.u5w4.repositories;

import com.epicenergyservices.u5w4.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DAOprovince extends JpaRepository<Province, Long> {
    List<Province> findByName(String name);
}
