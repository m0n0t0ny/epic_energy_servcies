package com.epicenergyservices.u5w4.entities;

import com.epicenergyservices.u5w4.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Addrress {
    @Id
    @GeneratedValue
    private UUID id;
    private String street;
    private String civic_number;
    private String location;
    private String postal_code;
    @ManyToOne
    @JoinColumn(name = "townhall_id")
    private TownHall townHall;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    public Addrress(String street, String civic_number, String location, String postal_code, TownHall townHall, AddressType addressType) {
        this.street = street;
        this.civic_number = civic_number;
        this.location = location;
        this.postal_code = postal_code;
        this.townHall = townHall;
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return "Addrress{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", civic_number='" + civic_number + '\'' +
                ", location='" + location + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", townHall=" + townHall +
                ", addressType=" + addressType +
                '}';
    }
}
