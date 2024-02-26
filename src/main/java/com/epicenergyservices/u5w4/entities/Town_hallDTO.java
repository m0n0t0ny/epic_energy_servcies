package com.epicenergyservices.u5w4.entities;

import java.util.UUID;

public class Town_hallDTO {
    private UUID id;
    private String name;
    private UUID provice_id;
    Town_hallDTO(UUID id, String name, UUID provice_id){
        if(id == null || name == null || provice_id == null){
            throw new InvalidPayloadException("Il payload non può contenere valori null");
        }
        this.id = id;
        this.name = name;
        this.provice_id = provice_id;
    }
    public static class InvalidPayloadException extends RuntimeException{
        public InvalidPayloadException (String message){
            super(message);
        }
    }
}
