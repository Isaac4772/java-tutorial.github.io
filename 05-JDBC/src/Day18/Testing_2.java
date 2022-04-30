package Day18;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class Testing_2 {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/testing";
        String username = "root";
        String password = "";

        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String insert = """
                    INSERT INTO employees(empNo, name, salary, city, birthday)VALUES(?,?,?,?,?)
                    """;

            PreparedStatement pstm = connection.prepareStatement(insert);
            pstm.setInt(1, 1001);
            pstm.setString(2, "Htet Htet");
            pstm.setDouble(3, 500000);
            pstm.setString(4, "Yangon");
            LocalDate dob = LocalDate.of(1995, 9, 1);
            pstm.setDate(5, Date.valueOf(dob));

            int result = pstm.executeUpdate();

            System.out.println((result > 0) ? "Insert succeed": "Insert failed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
