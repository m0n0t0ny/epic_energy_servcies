package com.epicenergyservices.u5w4.entities;

import com.epicenergyservices.u5w4.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)
  private UUID id;

  @Column(nullable = false)
  private String companyName;

  @Column(nullable = false, unique = true)
  private String vatNumber;

  @Column(nullable = false, unique = true)
  private String email;

  @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
  @Column(name = "insertion_date", nullable = false)
  private LocalDate insertionDate;

  @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
  @Column(name = "last_contact_date")
  private LocalDate lastContactDate;

  @Column(nullable = false)
  private double annualRevenue;

  @Column(name = "certified_email")
  private String certifiedEmail;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "contact_email")
  private String contactEmail;

  @Column(name = "contact_first_name")
  private String contactFirstName;

  @Column(name = "contact_last_name")
  private String contactLastName;

  @Column(name = "contact_phone_number")
  private String contactPhoneNumber;

  @Column(name = "company_logo")
  private String companyLogo;

  @Enumerated(EnumType.STRING)
  private ClientType clientType;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "legal_address_id", referencedColumnName = "id")
  private Address legalAddress;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "company_address_id", referencedColumnName = "id")
  private Address companyAddress;



  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Client(String companyName, String vatNumber, String email, LocalDate insertionDate, LocalDate lastContactDate, double annualRevenue, String certifiedEmail, String phoneNumber, String contactEmail, String contactFirstName, String contactLastName, String contactPhoneNumber, String companyLogo, ClientType clientType, Address legalAddress, Address companyAddress, User user) {
    this.companyName = companyName;
    this.vatNumber = vatNumber;
    this.email = email;
    this.insertionDate = insertionDate;
    this.lastContactDate = lastContactDate;
    this.annualRevenue = annualRevenue;
    this.certifiedEmail = certifiedEmail;
    this.phoneNumber = phoneNumber;
    this.contactEmail = contactEmail;
    this.contactFirstName = contactFirstName;
    this.contactLastName = contactLastName;
    this.contactPhoneNumber = contactPhoneNumber;
    this.companyLogo = companyLogo;
    this.clientType = clientType;
    this.legalAddress = legalAddress;
    this.companyAddress = companyAddress;
    this.user = user;
  }
}
