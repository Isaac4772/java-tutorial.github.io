package Day18;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ExecuteBatch {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/testing";
        String username = "root";
        String password = "";

        try(Connection connection = DriverManager.getConnection(url, username, password);
            Scanner input = new Scanner(System.in)) {
            String insert = "INSERT INTO employees(empNo, name, salary, city, birthday)VALUES(?,?,?,?,?)";

            PreparedStatement pstm = connection.prepareStatement(insert);
            for(var i = 1; i <= 3; i++){
                System.out.print("Enter employee no: ");
                pstm.setInt(1, Integer.parseInt(input.nextLine()));
                System.out.print("Enter employee's Name: ");
                pstm.setString(2, input.nextLine());
                System.out.print("Enter salary:");
                pstm.setDouble(3, Double.parseDouble(input.nextLine()));
                System.out.print("Enter city: ");
                pstm.setString(4, input.nextLine());
                System.out.print("Enter Birthday(yyyy-mm-dd): ");
                pstm.setDate(5, Date.valueOf(input.nextLine()));
                pstm.addBatch();
            }

            int[] result = pstm.executeBatch();

            System.out.println("No of rows: " + ((result != null)? result.length: 0));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
