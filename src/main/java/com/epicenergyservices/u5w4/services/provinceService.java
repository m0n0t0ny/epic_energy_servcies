package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.dao.DAOprovince;
import com.epicenergyservices.u5w4.entities.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class provinceService {
    @Autowired
    private DAOprovince daOprovince;

    public List<Province> getProvince(String name){
        return daOprovince.findByName(name);
    }
}
