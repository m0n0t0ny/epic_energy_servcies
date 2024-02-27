package com.epicenergyservices.u5w4.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "municipality")
public class Municipality {

    @Id
    private long id;
    private long codiceProvincia;
    private String progressivoComune;
    private String name;
    private String provinceName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;

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
