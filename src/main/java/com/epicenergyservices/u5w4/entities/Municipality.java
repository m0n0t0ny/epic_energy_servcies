package com.epicenergyservices.u5w4.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "municipality")
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long codiceProvincia;
    private String progressivoComune;
    private String name;
    private String provinceName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;

    public Municipality(long codiceProvincia, String progressivoComune, String name, String provinceName, Province province) {
        this.codiceProvincia = codiceProvincia;
        this.progressivoComune = progressivoComune;
        this.name = name;
        this.provinceName = provinceName;
        this.province = province;
    }

    @Override
    public String toString() {
        return "Municipality{" +
                "id=" + id +
                ", codiceProvincia=" + codiceProvincia +
                ", progressivoComune='" + progressivoComune + '\'' +
                ", name='" + name + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", province=" + province +
                '}';
    }
}
