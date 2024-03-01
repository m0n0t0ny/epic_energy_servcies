package com.epicenergyservices.u5w4.services;

import com.epicenergyservices.u5w4.dto.MunicipalityDTO;
import com.epicenergyservices.u5w4.entities.Address;
import com.epicenergyservices.u5w4.entities.Municipality;
import com.epicenergyservices.u5w4.entities.Province;
import com.epicenergyservices.u5w4.exceptions.NotFoundException;
import com.epicenergyservices.u5w4.repositories.MunicipalityRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MunicipalityServ {
    @Autowired
    private MunicipalityRep municipalityRep;
    @Autowired
    private ProvinceService provinceService;

    public Page<Municipality> getMunicipalitys(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return municipalityRep.findAll(pageable);
    }

    public Municipality getMunicipalityById(long id) {
        return municipalityRep.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Municipality saveMunicipality(MunicipalityDTO newMunicipality) {
        Province province = provinceService.findById(newMunicipality.province());
        return municipalityRep.save(
                new Municipality(newMunicipality.codiceProvincia(), newMunicipality.progressivoComune(), newMunicipality.name(), newMunicipality.provinceName(), province)
        );
    }

    public Municipality findAndUpdate(long municipalityId, Municipality updateMunicipality) {
        Municipality found = this.getMunicipalityById(municipalityId);
        found.setCodiceProvincia(updateMunicipality.getCodiceProvincia());
        found.setProgressivoComune(updateMunicipality.getProgressivoComune());
        found.setName(updateMunicipality.getName());
        found.setProvinceName(updateMunicipality.getProvinceName());
        found.setProvince(updateMunicipality.getProvince());
        return municipalityRep.save(found);

    }


    public void deleteMunicipality(long id) {
        Municipality municipality = this.getMunicipalityById(id);
        municipalityRep.delete(municipality);
    }
}
