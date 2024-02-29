package com.epicenergyservices.u5w4;

import com.epicenergyservices.u5w4.entities.Province;
import com.epicenergyservices.u5w4.repositories.ProvinceRepository;
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

@Component
public class Province_runner implements CommandLineRunner {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Value("${PG_URL}")
    private String URL;
    @Value("${PG_USERNAME}")
    String username;
    @Value("${PG_PASSWORD}")
    String password;


    private final String csvFilePath2 = "province-italiane.csv";

    Province province1 = new Province("SU", "Sud Sardegna", "Sardegna");


    @Override
    public void run(String... args) throws Exception {
        if (provinceRepository.findByInitials("SU") == null) {
            provinceRepository.save(province1);
        }
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath2));

            lineReader.readLine();


            String line;
            while ((line = lineReader.readLine()) != null) {

                String[] data = line.split(";");

                String initials = data[0];

                String name = data[1];
                String[] data2 = name.split(" ");
                String name2 = String.join("_", data2);
                String[] data3 = name2.split("-");
                String name3 = String.join("_", data3);

                String region = data[2];

                Province province = new Province(initials, name3, region);
                if (provinceRepository.findByInitials(initials) == null) {
                    provinceRepository.save(province);
                }
                switch (name3) {
                    case "Carbonia_Iglesias":
                    case "Medio_Campidano":
                    case "Ogliastra":
                    case "Olbia_Tempio":
                        provinceRepository.delete(provinceRepository.findByName(name3).get(0));
                        break;
                }


            }
            lineReader.close();


        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}
