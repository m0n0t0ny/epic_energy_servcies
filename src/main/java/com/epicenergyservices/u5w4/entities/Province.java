package com.epicenergyservices.u5w4.entities;

import jakarta.persistence.*;
import lombok.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Province {
    @Id
    private long id;
    private String initials;
    private String name;
    private String region;

    public Province(String initials, String name, String region) {
        this.initials = initials;
        this.name = name;
        this.region = region;
    }

    public Province(String initials, String name) {
        this.initials = initials;
        this.name = name;

    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initials='" + initials + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
