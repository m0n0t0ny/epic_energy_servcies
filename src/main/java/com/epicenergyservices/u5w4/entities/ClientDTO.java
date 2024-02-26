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

    private ClientDTO(UUID id, String company_name, String vat_number, String email, Date insertion_date, Date last_contact_date, Integer annual_revenue,
                      String certified_email, String phone_number, String contact_email, String contact_first_name, String contact_last_name,
                      String contact_phone_number, String company_logo, Type type, UUID legal_address_id, UUID company_address_id, UUID invoices_id,
                      UUID user_id){
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
public enum Type{
    PA,
    SAS,
    SPA,
    SRL
    }
}