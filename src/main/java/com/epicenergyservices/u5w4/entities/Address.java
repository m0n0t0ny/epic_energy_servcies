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

    @ManyToOne
    @JoinColumn(name = "town_hall_id")
    private Municipality municipality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressType type;

    public Address(String street, String civicNumber, String location, String postalCode, Municipality municipality, AddressType type) {
        this.street = street;
        this.civicNumber = civicNumber;
        this.location = location;
        this.postalCode = postalCode;
        this.municipality = municipality;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", civicNumber='" + civicNumber + '\'' +
                ", location='" + location + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", municipality=" + municipality +
                ", type=" + type +
                '}';
    }
}

