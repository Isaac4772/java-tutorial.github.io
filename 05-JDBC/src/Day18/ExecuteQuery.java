package Day18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExecuteQuery {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/testing";
        String username = "root";
        String password = "";

        try(Connection connection = DriverManager.getConnection(url, username, password)){
//            String query1 = "SELECT * FROM employees";
//            PreparedStatement pstm = connection.prepareStatement(query1);
//
//            ResultSet res = pstm.executeQuery();
//
//            while (res.next()){
//                System.out.println("EmpNo: " + res.getInt(1) );
//                System.out.println("Name: " + res.getString(2));
//                System.out.println("Birthday: " + res.getDate("birthday"));
//                System.out.println("Salary: "  + res.getString("salary"));

//            String query1 = "SELECT * FROM employees WHERE salary BETWEEN ? AND ?";
//            PreparedStatement pstm = connection.prepareStatement(query1);
//            pstm.setDouble(1,500000);
//            pstm.setDouble(2, 800000);
//
//            ResultSet res = pstm.executeQuery();
//
//            while (res.next()){
//                System.out.println("EmpNo: " + res.getInt(1) );
//                System.out.println("Name: " + res.getString(2));
//                System.out.println("Birthday: " + res.getDate("birthday"));
//                System.out.println("Salary: "  + res.getString("salary"));
//                System.out.println("----------");

            String query = "SELECT empNo, name FROM employees WHERE empNo IN(?,?,?)";

            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, 1001);
            pstm.setInt(2, 1002);
            pstm.setInt(3, 1004);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()) {
                System.out.println("No: " + rs.getInt("empNo"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("________");
            }
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
}
