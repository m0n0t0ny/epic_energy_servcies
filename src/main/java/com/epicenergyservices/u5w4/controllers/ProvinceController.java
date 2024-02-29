package com.epicenergyservices.u5w4.controllers;

import com.epicenergyservices.u5w4.dto.ProvinceDTO;
import com.epicenergyservices.u5w4.entities.Province;
import com.epicenergyservices.u5w4.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public Page<Province> getAllProvinces(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.provinceService.getAllProvince(page, size, orderBy);
    }


    @GetMapping("/{id}")
    public Province findById(@PathVariable long id) {
        return this.provinceService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Province saveNewProvince(@RequestBody ProvinceDTO provinceDTO){
        return this.provinceService.save(provinceDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Province findByIdAndUpdate(@PathVariable long id, @RequestBody Province updatedProvince) {

        return this.provinceService.findAndUpdate(id, updatedProvince);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.provinceService.deleteProvince(id);
    }

}
