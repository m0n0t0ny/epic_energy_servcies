package com.epicenergyservices.u5w4.entities;

import java.util.Date;
import java.util.UUID;

public class InvoicesDTO {
    private UUID id;
    private Date date;
    private double amount;
    private String status;
    public InvoicesDTO(UUID id, Date date, double amount, String status){
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.status = status;
    }
}
