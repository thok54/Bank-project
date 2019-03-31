package ejercicio.banco.util;

import java.sql.*;

public class DataBaseUtil {

    public static void main(String[] args) throws SQLException {
        Connection con = openConnections();
        executeQuery(con, " imposible query");
        showTables(con);
        closeConections(con);
    }

    public static Connection openConnections() throws SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/bank_project", "user", "user");
        return con;
    }

    public static void executeQuery(Connection con, String query) throws SQLException {
        try {
            Statement pstmt = con.createStatement();
            pstmt.executeUpdate(query);
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }
        catch (Exception e){
            System.out.println("Query: '"+ query+"' not possible");
        }
    }

    public static void showTables(Connection con) throws SQLException {
        //Reads BANKS table, returning results
        PreparedStatement pstmt = con.prepareStatement("select * from BANKS");
        ResultSet rs = pstmt.executeQuery();
        System.out.println("BANKS");
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");
            System.out.println(id + " " + name + ", in " + address);
        }
        //Reads ACCOUNTS table, returning results
        pstmt = con.prepareStatement("select * from ACCOUNTS");
        rs = pstmt.executeQuery();
        System.out.println("ACCOUNTS");
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            Float money = rs.getFloat("money");
            String iban = rs.getString("iban");
            System.out.println(id + " " + name + ", with $" + money + "; iban code: " + iban);
        }
        //Reads PAYMENTS table, returning results
        pstmt = con.prepareStatement("select * from PAYMENTS");
        rs = pstmt.executeQuery();
        System.out.println("PAYMENTS");
        while (rs.next()) {
            Integer id = rs.getInt("id");
            Integer bankId = rs.getInt("bank_id");
            Integer userId = rs.getInt("user_id");
            Float amount = rs.getFloat("amount");
            System.out.println(id + "; bank id: " + bankId + "; user id:" + userId + "; for an amount of $" + amount);
        }
        //Closing statement and ResultSet
        try {
            pstmt.close();
        } catch (Exception e) {
        }
        try {
            rs.close();
        } catch (Exception e) {
        }
    }

    public static void closeConections(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
        }
    }
}
