package com.epicenergyservices.u5w4.payloads;

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
        if (id == null || street == null || civic_number == null || location == null || postal_code == null ||
        town_hall_id == null || type == null){
            throw new InvalidPayloadException("Il payload non può contenere valori null");
        }
        this.id = id;
        this.street = street;
        this.civic_number = civic_number;
        this.location = location;
        this.postal_code = postal_code;
        this.town_hall_id = town_hall_id;
        this.type = type;
    }
    public static class InvalidPayloadException extends RuntimeException {
        public InvalidPayloadException(String message){
            super(message);
        }
    }
}
