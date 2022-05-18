package bms.bms.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/bookdb","root", "");
    }
}
