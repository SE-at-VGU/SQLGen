package main.sqlio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.naming.NamingException;

//mysql -u root -p [dbname] --local-infile --execute "LOAD DATA LOCAL INFILE 'car' INTO TABLE Car FIELDS TERMINATED BY ',' LINES STARTING BY '#' TERMINATED BY '\r\n' ("Car_id", "color"); SHOW WARNINGS";
//mysql -u root -p [dbname] --local-infile --execute "LOAD DATA LOCAL INFILE 'person' INTO TABLE Person FIELDS TERMINATED BY ',' LINES STARTING BY '#' TERMINATED BY '\r\n' ("Person_id", "name"); SHOW WARNINGS";
//mysql -u root -p [dbname] --local-infile --execute "LOAD DATA LOCAL INFILE 'ownership' INTO TABLE Ownership FIELDS TERMINATED BY ',' LINES STARTING BY '#' TERMINATED BY '\r\n' ("owners", "ownedCars"); SHOW WARNINGS";

public class CarPerson {
    static final int carSize = 1000000;
    static final int personSize = 100000;

    public static void main(String[] args) throws ClassNotFoundException,
        SQLException, NamingException, IOException {

        // Get the file reference
        Path path = Paths.get("car");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 1; i <= carSize; i++) {
                String value = "#".concat(String.valueOf(i)).concat(",")
                    .concat("'color_".concat(String.valueOf(i))).concat("\r\n");
                writer.write(value);
                writer.newLine();
            }
        }

        //
        path = Paths.get("person");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 1; i <= personSize; i++) {
                String value = "#".concat(String.valueOf(i)).concat(",")
                    .concat("'name_".concat(String.valueOf(i))).concat("\r\n");
                writer.write(value);
                writer.newLine();
            }
        }
        //
        path = Paths.get("ownership");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 1; i <= personSize; i++) {
                for (int j = 1; j <= 10; j++) {
                    String value = "#".concat(String.valueOf(i)).concat(",")
                        .concat(String.valueOf((i - 1) * 10 + j))
                        .concat("\r\n");
                    writer.write(value);
                    writer.newLine();
                }
            }
        }
    }
}

/*
 * loading large file mysql -uroot -p thesis_test --local-infile --
 * execute="LOAD DATA LOCAL INFILE 'thesis_car' INTO TABLE car FIELDS TERMINATED BY ',' LINES STARTING BY '#' ("
 * color"); SHOW WARNINGS"
 * 
 * mysql -uroot -p thesis_test --local-infile --
 * execute="LOAD DATA LOCAL INFILE 'thesis_person' INTO TABLE person FIELDS TERMINATED BY ',' LINES STARTING BY '#' ("
 * name"); SHOW WARNINGS"
 * 
 * mysql -uroot -p thesis_test --local-infile --
 * execute="LOAD DATA LOCAL INFILE 'thesis_employee' INTO TABLE employee FIELDS TERMINATED BY ',' LINES STARTING BY '#' ("
 * person"); SHOW WARNINGS"
 * 
 * mysql -uroot -p thesis_test --local-infile --
 * execute="LOAD DATA LOCAL INFILE 'thesis_car_person' INTO TABLE car_person FIELDS TERMINATED BY ',' LINES STARTING BY '#' ("
 * car","person"); SHOW WARNINGS"
 * 
 * 
 * 
 * size of a table SELECT table_name AS `car`, round(((data_length +
 * index_length) / 1024 / 1024), 2) `Size in MB` FROM information_schema.TABLES
 * WHERE table_schema = "thesis_test" AND table_name = "car";
 */