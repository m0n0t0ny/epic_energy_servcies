package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.dto.MunicipalityDTO;
import com.epicenergyservices.u5w4.entities.Municipality;
import com.epicenergyservices.u5w4.services.MunicipalityServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/municipality")
public class MunicipalityController {
    @Autowired
    private MunicipalityServ municipalityServ;
    
    @GetMapping
    public Page<Municipality> getAllMunicipilitys(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.municipalityServ.getMunicipalitys(page, size, orderBy);
    }


    @GetMapping("/{id}")
    public Municipality findById(@PathVariable long id) {
        return this.municipalityServ.getMunicipalityById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Municipality saveNewMunicipility(@RequestBody MunicipalityDTO municipalityDTO){
        return this.municipalityServ.saveMunicipality(municipalityDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Municipality findByIdAndUpdate(@PathVariable long id, @RequestBody Municipality updatedMunicipality) {

        return this.municipalityServ.findAndUpdate(id, updatedMunicipality);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.municipalityServ.deleteMunicipality(id);
    }
}
