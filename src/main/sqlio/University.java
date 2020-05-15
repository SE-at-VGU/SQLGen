package main.sqlio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.naming.NamingException;

// mysql -u root -p vgu10000 --local-infile --execute "LOAD DATA LOCAL INFILE 'student' INTO TABLE Student FIELDS TERMINATED BY ',' LINES STARTING BY '#' TERMINATED BY '\r\n' ("student_id", "name", "email"); SHOW WARNINGS";
// mysql -u root -p vgu10000 --local-infile --execute "LOAD DATA LOCAL INFILE 'lecturer' INTO TABLE Lecturer FIELDS TERMINATED BY ',' LINES STARTING BY '#' TERMINATED BY '\r\n' ("lecturer_id", "name", "email"); SHOW WARNINGS";
// mysql -u root -p vgu10000 --local-infile --execute "LOAD DATA LOCAL INFILE 'enrollment' INTO TABLE Enrollment FIELDS TERMINATED BY ',' LINES STARTING BY '#' TERMINATED BY '\r\n' ("students", "lecturers"); SHOW WARNINGS";

public class University {
    static final int studentSize = 10000;
    static final int lecturerSize = 3;

    public static void main(String[] args) throws ClassNotFoundException,
        SQLException, NamingException, IOException {

        Path path = Paths.get("student");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            String An = "#An,An,An@vgu.edu.vn\r\n";
            writer.write(An);
            writer.newLine();
            String Chau = "#Chau,Chau,Chau@vgu.edu.vn\r\n";
            writer.write(Chau);
            writer.newLine();
            String Hoang = "#Hoang,Hoang,Hoang@vgu.edu.vn\r\n";
            writer.write(Hoang);
            writer.newLine();
            String Thanh = "#Thanh,Thanh,Thanh@vgu.edu.vn\r\n";
            writer.write(Thanh);
            writer.newLine();
            String Nam = "#Nam,Nam,Nam@vgu.edu.vn\r\n";
            writer.write(Nam);
            writer.newLine();

            for (int i = 6; i <= studentSize; i++) {
                String value = "#".concat("'id".concat(String.valueOf(i)))
                    .concat(",").concat("'name".concat(String.valueOf(i)))
                    .concat(",").concat(String.valueOf(i).concat("@vgu.edu.vn"))
                    .concat("\r\n");
                writer.write(value);
                writer.newLine();
            }
        }

        //
        path = Paths.get("lecturer");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            String Huong = "#Huong,Huong,Huong@vgu.edu.vn\r\n";
            writer.write(Huong);
            writer.newLine();
            String Hieu = "#Hieu,Hieu,Hieu@vgu.edu.vn\r\n";
            writer.write(Hieu);
            writer.newLine();
            String Manuel = "#Manuel,Manuel,Manuel@vgu.edu.vn\r\n";
            writer.write(Manuel);
            writer.newLine();
        }

        //
        path = Paths.get("enrollment");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            String AnManuel = "#An,Manuel\r\n";
            writer.write(AnManuel);
            writer.newLine();
            String HoangManuel = "#Hoang,Manuel\r\n";
            writer.write(HoangManuel);
            writer.newLine();
            String ChauManuel = "#Chau,Manuel\r\n";
            writer.write(ChauManuel);
            writer.newLine();
            String ChauHuong = "#Chau,Huong\r\n";
            writer.write(ChauHuong);
            writer.newLine();
            for (int i = 6; i < 106; i++) {
                String enrollManuel = "#"
                    .concat("'id".concat(String.valueOf(i))).concat(",")
                    .concat("Manuel").concat("\r\n");
                writer.write(enrollManuel);
                writer.newLine();
                String enrollHuong = "#".concat("'id".concat(String.valueOf(i)))
                    .concat(",").concat("Huong").concat("\r\n");
                writer.write(enrollHuong);
                writer.newLine();
            }
            for (int i = 106; i <= 10000; i++) {
                String enrollManuel = "#"
                    .concat("'id".concat(String.valueOf(i))).concat(",")
                    .concat("Hieu").concat("\r\n");
                writer.write(enrollManuel);
                writer.newLine();
            }
        }
    }
}
