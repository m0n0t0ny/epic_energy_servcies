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
  private String companyName;

  @Column(nullable = false, unique = true)
  private String vatNumber;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "insertion_date", nullable = false)
  private LocalDate insertionDate;

  @Column(name = "last_contact_date")
  private LocalDate lastContactDate;

  @Column(nullable = false)
  private Integer annualRevenue;

  @Column(name = "certified_email")
  private String certifiedEmail;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "contact_email")
  private String contactEmail;

  @Column(name = "contact_first_name")
  private String contactFirst_name;

  @Column(name = "contact_last_name")
  private String contactLast_name;

  @Column(name = "contact_phone_number")
  private String contactPhone_number;

  @Column(name = "company_logo")
  private String companyLogo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "legal_address_id", referencedColumnName = "id")
  private Address legalAddress;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "company_address_id", referencedColumnName = "id")
  private Address companyAddress;

  @OneToOne(mappedBy = "client")
  private Invoice invoice;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
