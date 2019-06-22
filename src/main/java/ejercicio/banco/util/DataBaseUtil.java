package ejercicio.banco.util;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DataBaseUtil {

    public static void main(String[] args) {
        DataBaseUtil util = new DataBaseUtil();

        Connection con = util.startConnection();
        util.executeQuery(con, " imposible query");
        try {
            showTables(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        util.closeConections(con);
    }

    public Connection startConnection() {
        Connection con = null;
        try {
            con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/bank_project", "user", "user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void executeQuery(Connection con, String query) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            try {
                stmt.close();
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.println("Query: '" + query + "' not possible");
        }
    }

    public static void showTables(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("select * from BANKS");
        ResultSet rs = pstmt.executeQuery();
        System.out.println("BANKS");
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");
            System.out.println(id + " " + name + ", in " + address);
        }
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
        pstmt = con.prepareStatement("select * from PAYMENTS");
        rs = pstmt.executeQuery();
        System.out.println("PAYMENTS");
        while (rs.next()) {
            Integer id = rs.getInt("id");
            Integer bankId = rs.getInt("bankId");
            Integer userId = rs.getInt("userId");
            Float amount = rs.getFloat("amount");
            System.out.println(id + "; bank id: " + bankId + "; user id:" + userId + "; for an amount of $" + amount);
        }
        try {
            pstmt.close();
        } catch (Exception e) {
        }
        try {
            rs.close();
        } catch (Exception e) {
        }
    }

    public void closeConections(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
        }
    }
}
