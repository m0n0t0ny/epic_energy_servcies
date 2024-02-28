package com.epicenergyservices.u5w4.repositories;

import com.epicenergyservices.u5w4.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    List<Province> findByName(String name);

}
