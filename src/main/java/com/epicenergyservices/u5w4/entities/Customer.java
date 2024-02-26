package com.epicenergyservices.u5w4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Customer {
  @Id
  @Setter(AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String companyName;
  private String vatNumber;
  private String email;
  private LocalDate insertionDate;
  private LocalDate lastContactDate;
  private Double annualRevenue;
  private String pec;
  private String phone;
  private String contactEmail;
  private String contactFirstName;
  private String contactLastName;
  private String contactPhone;
  private String CompanyLogo;
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Address> addresses;
}
