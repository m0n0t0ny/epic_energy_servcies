package com.epicenergyservices.u5w4.entities;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.UUID;

public class ClientDTO {
    private UUID id;
    private String company_name;
    private String vat_number;
    private String email;
    private Date insertion_date;
    private Date last_contact_date;
    private Integer annual_revenue;
    private String certified_email;
    private String phone_number;
    private String contact_email;
    private String contact_first_name;
    private String contact_last_name;
    private String contact_phone_number;
    private String company_logo;
    private Type type;
    private UUID legal_address_id;
    private UUID company_address_id;
    private UUID invoices_id;
    private UUID user_id;

    private ClientDTO( UUID id, String company_name, String vat_number, String email, Date insertion_date, Date last_contact_date, Integer annual_revenue,
                      String certified_email, String phone_number, String contact_email, String contact_first_name, String contact_last_name,
                      String contact_phone_number, String company_logo, Type type, UUID legal_address_id, UUID company_address_id, UUID invoices_id,
                      UUID user_id ){
        if ( id == null || company_name == null || vat_number == null || email == null || insertion_date == null || last_contact_date == null ||
            annual_revenue == null || certified_email == null || phone_number == null || contact_email == null || contact_first_name == null ||
            contact_last_name == null || contact_phone_number == null || company_logo == null || type == null || legal_address_id == null ||
            company_address_id == null || invoices_id == null ||user_id == null ){
            throw new InvalidPayloadException("Il payload non può contenere valori null");
        }
    this.id = id;
    this.company_name = company_name;
    this.vat_number = vat_number;
    this.email = email;
    this.insertion_date = insertion_date;
    this.last_contact_date = last_contact_date;
    this.annual_revenue = annual_revenue;
    this.certified_email = certified_email;
    this.phone_number = phone_number;
    this.contact_email = contact_email;
    this.contact_first_name = contact_first_name;
    this.contact_last_name = contact_last_name;
    this.contact_phone_number = contact_phone_number;
    this.company_logo = company_logo;
    this.type = type;
    this.legal_address_id = legal_address_id;
    this.company_address_id = company_address_id;
    this.invoices_id = invoices_id;
    this.user_id = user_id;
    }
    public static class InvalidPayloadException extends RuntimeException {
        public InvalidPayloadException(String message){
            super(message);
        }
    }
public enum Type{
    PA,
    SAS,
    SPA,
    SRL
    }
}