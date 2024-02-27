package com.epicenergyservices.u5w4;

import com.epicenergyservices.u5w4.entities.Province;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class THrunner implements CommandLineRunner {
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

            String sql = "INSERT INTO town_hall (cap, id , name, province_name) VALUES ( ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            lineReader.readLine();

            long count = 1;
            String line;
            while ((line = lineReader.readLine()) != null) {
                String[] data = line.split(";");
                long CAP= Long.parseLong(data[0]);
                long id = count++;
//                long id= Long.parseLong(data[1]);
                String name=data[2];
                String nameProvince=data[3];

                statement.setLong(1,CAP);
                statement.setLong(2,id);
                statement.setString(3,name);
                statement.setString(4,nameProvince);

//                statement.addBatch();
                statement.executeUpdate();

            }
            lineReader.close();
//            statement.executeBatch();
            connection.commit();
            connection.close();
        }catch (IOException ex) {
            System.err.println(ex);
        }
        catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
