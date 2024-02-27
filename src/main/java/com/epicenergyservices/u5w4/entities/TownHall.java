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
    private long CAP;
//    @ManyToOne
//    @JoinColumn(name = "province_id")
//    private Province province;
    private String name;
    private String provinceName;

    @Override
    public String toString() {
        return "TownHall{" +
                "id=" + id +
                ", CAP=" + CAP +
                ", name='" + name + '\'' +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }
}
