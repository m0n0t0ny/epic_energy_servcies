package com.epicenergyservices.u5w4.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TownHall {
    @Id
//    @GeneratedValue
    private long id;
    private long codiceProvincia;
    private String progressivoComune;
    private String name;
    private String provinceName;
//    @ManyToOne
//    @JoinColumn(name = "province_id")
//    private Province province;


}
