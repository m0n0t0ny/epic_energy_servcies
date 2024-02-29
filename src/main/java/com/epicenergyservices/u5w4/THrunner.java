package com.epicenergyservices.u5w4;

import com.epicenergyservices.u5w4.entities.Municipality;
import com.epicenergyservices.u5w4.entities.Province;
import com.epicenergyservices.u5w4.repositories.MunicipalityRep;
import com.epicenergyservices.u5w4.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Component
public class THrunner implements CommandLineRunner {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private MunicipalityRep municipalityRep;
    @Value("${PG_URL}")
    private String URL;
    @Value("${PG_USERNAME}")
    String username;
    @Value("${PG_PASSWORD}")
    String password;
    private final String csvFilePath = "comuni-italiani.csv";

    @Override
    public void run(String... args) throws Exception {
        try {


            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            lineReader.readLine();


            long progr = 1;
            String line;
            while ((line = lineReader.readLine()) != null) {
                String[] data = line.split(";");

                long codProvincia = Long.parseLong(data[0]);
                String progrComune = data[1];
                if (Objects.equals(progrComune, "#RIF!")) {
                    progrComune = String.valueOf(progr++);
                }
                String name = data[2];
                String nameProvince = data[3];
                String[] data2 = nameProvince.split(" ");
                String nameProvince2 = String.join("_", data2);
                String[] data3 = nameProvince2.split("-");
                String nameProvince3 = String.join("_", data3);

                List<Province> provinces = this.provinceService.getProvince(nameProvince2);

                long provinceId = 1;
                if (!provinces.isEmpty()) {
                    provinceId = provinces.get(0).getId();
                }
                switch (nameProvince3) {
                    case "Monza_e_della_Brianza":
                        provinceId = 62;
                        break;
                    case "Bolzano/Bozen":
                        provinceId = 19;
                        break;
                    case "Reggio_nell'Emilia":
                        provinceId = 85;
                        break;
                    case "Forlì_Cesena":
                        provinceId = 40;
                        break;
                    case "Massa_Carrara":
                        provinceId = 56;
                        break;
                    case "Pesaro_e_Urbino":
                        provinceId = 74;
                        break;
                    case "Barletta_Andria_Trani":
                        provinceId = 13;
                        break;
                    case "Valle_d'Aosta/Vallée_d'Aoste":
                        provinceId = 6;
                        break;

                }

                Province province = provinceService.findById(provinceId);
                Municipality municipality = new Municipality(codProvincia, progrComune, name, nameProvince3, province);
                if (municipalityRep.findByName(name) == null) {
                    municipalityRep.save(municipality);
                }

            }
            lineReader.close();

        } catch (
                IOException ex) {
            System.err.println(ex);
        }
    }
}
