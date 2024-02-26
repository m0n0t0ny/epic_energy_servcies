package com.epicenergyservices.u5w4.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)
  private UUID id;

  @Column(nullable = false)
  private String company_name;

  @Column(nullable = false, unique = true)
  private String vat_number;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "insertion_date", nullable = false)
  private LocalDate insertion_date;

  @Column(name = "last_contact_date")
  private LocalDate last_contact_date;

  @Column(nullable = false)
  private Integer annual_revenue;

  @Column(name = "certified_email")
  private String certified_email;

  @Column(name = "phone_number")
  private String phone_number;

  @Column(name = "contact_email")
  private String contact_email;

  @Column(name = "contact_first_name")
  private String contact_first_name;

  @Column(name = "contact_last_name")
  private String contact_last_name;

  @Column(name = "contact_phone_number")
  private String contact_phone_number;

  @Column(name = "company_logo")
  private String company_logo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "legal_address_id", referencedColumnName = "id")
  private Address legalAddress;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "company_address_id", referencedColumnName = "id")
  private Address companyAddress;

  @OneToOne(mappedBy = "client")
  private Invoice invoice;

  @OneToOne(mappedBy = "client")
  private User user;
}
