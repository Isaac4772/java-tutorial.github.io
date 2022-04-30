package Day18.bookmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    public static Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bookdb","root", "");

        return connection;
    }
}
