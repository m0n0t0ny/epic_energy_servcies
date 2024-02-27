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
    private long id;
    private long codiceProvincia;
    private String progressivoComune;
    private String name;
    private String provinceName;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @Override
    public String toString() {
        return "TownHall{" +
                "id=" + id +
                ", codiceProvincia=" + codiceProvincia +
                ", progressivoComune='" + progressivoComune + '\'' +
                ", name='" + name + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", province=" + province +
                '}';
    }
}
