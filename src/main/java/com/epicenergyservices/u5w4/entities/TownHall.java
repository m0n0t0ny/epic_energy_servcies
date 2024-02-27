package com.epicenergyservices.u5w4.entities;


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
@Table(name = "town_halls")
public class TownHall {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "province_id")
  private Province province;
}
