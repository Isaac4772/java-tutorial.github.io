package Day17;

import java.sql.*;

public class Testing_1 {
    public static void main(String[] args) {
        try(Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testing", "root", "")){
            System.out.println("Create connection Object");

            //create statement
//            Statement stm = con.createStatement();
//
////            query define
//            String insert = "INSERT INTO users(name, email, password)VALUES('Hla Hla', 'Hla@gmail.com', 'hla123')";
//
//            int result = stm.executeUpdate(insert);
//            System.out.println("No of rows: " + result);

//            String insert = "INSERT INTO users(username,email,password)VALUES(?,?,?)";
////          Prepared Statement
//            PreparedStatement pstm = con.prepareStatement(insert);
//            pstm.setString(1, "Yuki");
//            pstm.setString(2, "yuki@gmail.com");
//            pstm.setString(3, "yuki123");

//          execute query
//            int result = pstm.executeUpdate();
//            System.out.println("Numbers of affacted row: " + result);

//            String query = "{call saveUsers(?,?,?)}";
//            CallableStatement cstm = con.prepareCall(query);
//            cstm.setString(1,"Isaac");
//            cstm.setString(2, "isaac@gmail.com");
//            cstm.setString(3, "isaac345");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
