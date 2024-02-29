package com.epicenergyservices.u5w4.payloads;

import java.util.Date;
import java.util.UUID;

public class InvoicesDTO {
    private UUID id;
    private Date date;
    private double amount;
    private String status;
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