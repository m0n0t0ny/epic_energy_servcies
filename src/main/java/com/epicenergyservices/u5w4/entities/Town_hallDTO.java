package com.epicenergyservices.u5w4.entities;

import java.util.UUID;

public class Town_hallDTO {
    private UUID id;
    private String name;
    private UUID provice_id;
    Town_hallDTO(UUID id, String name, UUID provice_id){
        this.id = id;
        this.name = name;
        this.provice_id = provice_id;
    }
}
