package com.epicenergyservices.u5w4.entities;

import com.epicenergyservices.u5w4.enums.AddressType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "addresses")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Column(nullable = false)
  private String street;

  @Column(name = "civic_number", nullable = false)
  private String civicNumber;

  @Column(nullable = false)
  private String location;

  @Column(name = "postal_code", nullable = false)
  private String postalCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "town_hall_id")
  private TownHall townHall;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AddressType type;
}

