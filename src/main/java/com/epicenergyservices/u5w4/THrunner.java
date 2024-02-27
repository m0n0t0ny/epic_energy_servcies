package com.epicenergyservices.u5w4;

import com.epicenergyservices.u5w4.dao.DAOprovince;
import com.epicenergyservices.u5w4.entities.Province;
import com.epicenergyservices.u5w4.services.provinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class THrunner implements CommandLineRunner {
    @Autowired
    private provinceService provinceService;
    @Value("${PG_URL}")
    private String URL;
    @Value("${PG_USERNAME}")
    String username;
    @Value("${PG_PASSWORD}")
    String password;
    private final String csvFilePath = "comuni-italiani.csv";
    Connection connection = null;

    @Override
    public void run(String... args) throws Exception {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO town_hall (id, codice_provincia, progressivo_comune , name, province_name, province_id) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            //crea un nuovo file.csv
            BufferedWriter lineWriter = new BufferedWriter(new FileWriter("newFile.csv"));
            //leggi il vecchio file

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            lineReader.readLine();
            // Leggi i titoli
            String header = lineReader.readLine();

            //scrivi l'header sul nuovo file
            lineWriter.write(header);
            lineWriter.newLine();

            long count = 1;
            long progr = 1;
            String line;
            while ((line = lineReader.readLine()) != null) {
                String[] data = line.split(";");
                long codProvincia = Long.parseLong(data[0]);
                String progrComune = data[1];
                if (Objects.equals(progrComune, "#RIF!")) {
                    progrComune = String.valueOf(progr++);
                }
                long id = count++;
                String name = data[2];
                String nameProvince = data[3];
                String[] data2 = nameProvince.split(" ");
                String nameProvince2 = String.join("_", data2);

//                //mettendo meno uno nel caso non ci sia la relazione, il risultato sarà null
                List<Province> province = this.provinceService.getProvince(nameProvince2);

                long provinceId = 1;
                if (!province.isEmpty()) {
                    provinceId = province.get(0).getId();
                }
                lineWriter.write(id + ";" + codProvincia + ";" + progrComune + ";" + name + ";" + nameProvince2);
                lineWriter.newLine();

                statement.setLong(1, id);
                statement.setLong(2, codProvincia);
                statement.setString(3, progrComune);
                statement.setString(4, name);
                statement.setString(5, nameProvince2);
                statement.setLong(6,provinceId);

                System.out.println(province);
                statement.executeUpdate();

            }
            lineReader.close();
            lineWriter.close();

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
