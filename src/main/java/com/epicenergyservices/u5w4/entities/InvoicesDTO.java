package com.epicenergyservices.u5w4.entities;

import java.util.Date;
import java.util.UUID;

public class InvoicesDTO {
    public UUID id;
    public Date date;
    public double amount;
    public String status;
    public InvoicesDTO(UUID id, Date date, double amount, String status){
        if( id == null || date == null || status == null){
            throw new InvalidPayloadException("Il payload non può avere valori null");
        }
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.status = status;
    }
    public static class InvalidPayloadException extends RuntimeException{
        public InvalidPayloadException(String message){
        super(message);
        }
    }
}
