package com.epicenergyservices.u5w4;

import com.epicenergyservices.u5w4.entities.Province;
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
    private ProvinceService provinceSer;
    @Value("${PG_URL}")
    private String URL;
    @Value("${PG_USERNAME}")
    String username;
    @Value("${PG_PASSWORD}")
    String password;


    private final String csvFilePath2 = "province-italiane.csv";
    Connection connection = null;

    Province province1= new Province("SU","Sud Sardegna");


    @Override
    public void run(String... args) throws Exception {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO province (id,initials,name, region) VALUES (?, ?, ?, ?)";


            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath2));

            lineReader.readLine();

            provinceSer.save(province1);
            long count = 1;
            String line;
            while ((line = lineReader.readLine()) != null) {

                String[] data = line.split(";");

                long id = count++;

                String initials = data[0];

                String name = data[1];
                String[] data2 = name.split(" ");
                String name2 = String.join("_", data2);
                String[] data3 = name2.split("-");
                String name3 = String.join("_", data3);

                String region = data[2];


                //inserisci i dati
                statement.setLong(1, id);
                statement.setString(2, initials);
                statement.setString(3, name3);
                statement.setString(4, region);


                //metti i dati de db
                statement.executeUpdate();
            }
            lineReader.close();


            connection.commit();
            connection.close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
