package com.epicenergyservices.u5w4.entities;

import java.util.UUID;

public class provinceDTO {
    private UUID id;
    private String name;
    private String initials;
    provinceDTO(UUID id, String name, String initials){
        if( id == null || name == null || initials == null){
            throw new InvalidPayloadException("Il payload non può avere valori null");
        }
        this.id = id;
        this.name = name;
        this.initials = initials;
    }
    public static class InvalidPayloadException extends RuntimeException{
        public InvalidPayloadException (String message){
            super(message);
        }
    }
}
