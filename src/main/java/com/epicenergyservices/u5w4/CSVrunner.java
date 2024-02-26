package com.epicenergyservices.u5w4;

import com.epicenergyservices.u5w4.entities.Province;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class CSVrunner implements CommandLineRunner {

    @Value("${PG_URL}")
    private String URL;
    @Value("${PG_USERNAME}")
    String username;
    @Value("${PG_PASSWORD}")
    String password;


    private final String csvFilePath = "comuni-italiani.csv";
    private final String csvFilePath2 = "province-italiane.csv";
    Connection connection = null;

    @Override
    public void run(String... args) throws Exception {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO province (id, initials,name, region) VALUES (?, ?, ?, ?)";


            PreparedStatement statement = connection.prepareStatement(sql);

            //crea un nuovo file.csv
            BufferedWriter lineWriter = new BufferedWriter(new FileWriter("newFile.csv"));
            //leggi il vecchio file
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath2));

            // Leggi i titoli
            String header = lineReader.readLine();
            //scrivi l'header sul nuovo file
            lineWriter.write(header);
            lineWriter.newLine();

            long count = 0;
            String line;
            while ((line = lineReader.readLine()) != null) {
                String[] data = line.split(";");
                long id = count++;
                //mi prendo le colonne dall'array del vecchio file
                String initials = data[0];
                String name = data[1];
                String region = data[2];

                //scrivi l'id all'inizio della riga e poi passi alla prossima
                lineWriter.write(id + ";" + line);
                lineWriter.newLine();

                //inserisci i dati
                statement.setLong(1, id);
                statement.setString(2, initials);
                statement.setString(3, name);
                statement.setString(4, region);


//                statement.addBatch();
                System.out.println(statement);

                //metti i dati de db
                statement.executeUpdate();
            }
//            statement.executeBatch();

            lineWriter.close();
            lineReader.close();

            connection.commit();
            connection.close();
        } catch (IOException ex) {
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
