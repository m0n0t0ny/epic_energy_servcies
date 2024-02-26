package com.epicenergyservices.u5w4.entities;

import java.util.UUID;

public class provinceDTO {
    private UUID id;
    private String name;
    private String initials;
    provinceDTO(UUID id, String name, String initials){
        this.id = id;
        this.name = name;
        this.initials = initials;
    }
}
