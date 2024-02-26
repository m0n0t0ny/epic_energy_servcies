package com.epicenergyservices.u5w4.entities;

import java.lang.reflect.Type;
import java.util.UUID;

public class addressesDTO {
    private UUID id;
    private String street;
    private String civic_number;
    private String location;
    private String postal_code;
    private UUID town_hall_id;
    private Type type;
    public addressesDTO(UUID id, String street, String civic_number, String location, String postal_code,
                        UUID town_hall_id, Type type){
        this.id = id;
        this.street = street;
        this.civic_number = civic_number;
        this.location = location;
        this.postal_code = postal_code;
        this.town_hall_id = town_hall_id;
        this.type = type;
    }
}
