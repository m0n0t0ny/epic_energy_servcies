package com.epicenergyservices.u5w4.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Addrress {
    @Id
    @GeneratedValue
    private UUID id;
//    private String street;
//    private String civic_number;
//    private String location;
//    private String cap
}
