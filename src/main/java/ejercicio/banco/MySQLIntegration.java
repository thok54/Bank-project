package ejercicio.banco;

import java.sql.*;

//TODO: Not sure where to do this
public class MySQLIntegration {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Register driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Creates Connection
        Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/bank_project", "user", "user");

        //Statements
        Statement stmt = con.createStatement();

        //Inserts
        String insertBankSql = "INSERT INTO BANKS(id, name, address)"
                + " VALUES(1, 'bestBank', 'right here'), (3, 'Worst ATM', 'over there')";
        stmt.executeUpdate(insertBankSql);

        String insertAccountSql = "INSERT INTO Accounts(id, name, money, iban)"
                + " VALUES(1, 'Peter', 3.00, 'PIPIRANA87'), (2, 'Aurelio', 8.49, 'SATURN15STINKS')";
        stmt.executeUpdate(insertAccountSql);

        String insertPaymentSql = "INSERT INTO PAYMENTS(id, bank_id, user_id, amount)"
                + " VALUES(1, 1, 1, 1.87), (2, 3, 2, 3.41)";
        stmt.executeUpdate(insertPaymentSql);

        //Show tables
        String selectBankSql = "SELECT * FROM BANKS";
        ResultSet resulBanktSet = stmt.executeQuery(selectBankSql);

        String selectAccountSql = "SELECT * FROM ACCOUNTS";
        ResultSet resultAccountSet = stmt.executeQuery(selectAccountSql);

        String selectPaymentSql = "SELECT * FROM PAYMENTS";
        ResultSet resultPaymentSet = stmt.executeQuery(selectPaymentSql);

    }
}
