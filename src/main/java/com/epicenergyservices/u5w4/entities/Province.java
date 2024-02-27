package com.epicenergyservices.u5w4.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "provinces")
public class Province {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)
  @Column(updatable = false, nullable = false)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, length = 2)
  private String initials;

  @OneToMany(mappedBy = "province")
  private Set<TownHall> townHalls;
}
