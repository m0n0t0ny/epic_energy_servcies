package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.repositories.DAOprovince;
import com.epicenergyservices.u5w4.entities.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private DAOprovince daOprovince;


    public List<Province> getProvince(String name){
        return daOprovince.findByName(name);
    }
    public Province save(Province province){
        return daOprovince.save(province);
    }
}
