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
import java.util.*;

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


            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

            lineReader.readLine();


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
                String[] data3 = nameProvince2.split("-");
                String nameProvince3 = String.join("_", data3);

                List<Province> province = this.provinceService.getProvince(nameProvince2);

                long provinceId = 1;
                if (!province.isEmpty()) {
                    provinceId = province.get(0).getId();
                }
                switch (nameProvince3) {
                    case "Monza_e_della_Brianza":
                        provinceId = 59;
                        break;
                    case "Bolzano/Bozen":
                        provinceId = 16;
                        break;
                    case "Reggio_nell'Emilia":
                        provinceId = 82;
                        break;
                    case "Forlì_Cesena":
                        provinceId = 37;
                        break;
                    case "Massa_Carrara":
                        provinceId = 53;
                        break;
                    case "Pesaro_e_Urbino":
                        provinceId = 71;
                        break;
                    case "Barletta_Andria_Trani":
                        provinceId = 10;
                        break;
                    case "Verbano_Cusio_Ossola":
                        provinceId = 104;
                        break;
                    case "Valle_d'Aosta/Vallée_d'Aoste":
                        provinceId = 4;
                        break;

                }
                List<String> iglesias = new ArrayList<>();
                iglesias.addAll(Arrays.asList(
                        "Buggerru", "Calasetta", "Carbonia", "Carloforte", "Domusnovas", "Fluminimaggiore",
                        "Giba", "Gonnesa", "Iglesias", "Masainas", "Musei", "Narcao", "Nuxis", "Perdaxius",
                        "Piscinas", "Portoscuso", "San Giovanni Suergiu", "San Nicolo", "Sant'Anna Arresi",
                        "Sant'Antioco", "Tratalias", "Villamassargia", "Villaperuccio"
                ));

                if(iglesias.stream().anyMatch(t->t.equals(name))){
                    provinceId=23;
                }

                List<String> medioCampidano = new ArrayList<>();
                medioCampidano.addAll(Arrays.asList(
                        "Arbus", "Barumini", "Collinas", "Furtei", "Genoni", "Gesturi", "Gonnosfanadiga",
                        "Gonnostramatza", "Guspini", "Las Plassas", "Lunamatrona", "Pabillonis", "Samassi",
                        "San Gavino Monreale", "Sanluri", "Sardara", "Segariu", "Serrenti", "Setzu", "Siddi",
                        "Tuili", "Turri", "Ussaramanna", "Villacidro", "Villamar", "Villanovaforru",
                        "Villanovafranca", "Villaperuccio"
                ));
                if(medioCampidano.stream().anyMatch(t->t.equals(name))){
                    provinceId=56;
                }

                List<String> ogliastra = new ArrayList<>();
                ogliastra.addAll(Arrays.asList(
                        "Arzana", "Bari Sardo", "Baunei", "Cardedu", "Elini", "Gairo", "Ierzu", "Ilbono",
                        "Jerzu", "Lanusei", "Lotzorai", "Osini", "Perdasdefogu", "Seui", "Talana", "Tertenia",
                        "Tortolì", "Triei", "Ulassai", "Urzulei", "Ussassai", "Villagrande Strisaili"
                ));
                if(ogliastra.stream().anyMatch(t->t.equals(name))){
                    provinceId=64;
                }

                List<String> olbiaTempio = new ArrayList<>();
                olbiaTempio.addAll(Arrays.asList(
                        "Aggius", "Aglientu", "Alà dei Sardi", "Arzachena", "Badesi", "Berchidda", "Bortigiadas",
                        "Budoni", "Calangianus", "Golfo Aranci", "Loiri Porto San Paolo", "Luogosanto", "Luras",
                        "Monti", "Olbia", "Oschiri", "Padru", "Palau", "Sant'Antonio di Gallura", "Santa Teresa Gallura",
                        "Santo Stefano di Sessanio", "Tempio Pausania", "Tergu", "Trinità d'Agultu e Vignola", "Viddalba"
                ));
                if(olbiaTempio.stream().anyMatch(t->t.equals(name))){
                    provinceId=65;
                }

                statement.setLong(1, id);
                statement.setLong(2, codProvincia);
                statement.setString(3, progrComune);
                statement.setString(4, name);
                statement.setString(5, nameProvince3);
                statement.setLong(6, provinceId);

                System.out.println(province);
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
