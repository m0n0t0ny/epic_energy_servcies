package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.dto.ProvinceDTO;
import com.epicenergyservices.u5w4.entities.Province;
import com.epicenergyservices.u5w4.exceptions.NotFoundException;
import com.epicenergyservices.u5w4.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;


    public List<Province> getProvince(String name){
        return provinceRepository.findByName(name);
    }
    public Province findById(long id){
        return provinceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public Province save(ProvinceDTO province){

        return provinceRepository.save(new Province(
                province.initials(), province.name(), province.region()
        ));
    }
}
