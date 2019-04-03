package ejercicio.banco;

import java.sql.*;

public class MySQLIntegration {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Creates Connection
        Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/bank_project", "user", "user");

        //Statements
        Statement stmt = con.createStatement();

        //Inserts
        String insertBankSql = "INSERT INTO BANKS(name, address)"
                + " VALUES('bestBank', 'right here'), ('Worst ATM', 'over there')";
        stmt.executeUpdate(insertBankSql);

        String insertAccountSql = "INSERT INTO ACCOUNTS(name, money, iban)"
                + " VALUES('Peter', 3.00, 'PIPIRANA87'), ('Aurelio', 8.49, 'SATURN15STINKS')";
        stmt.executeUpdate(insertAccountSql);

        String insertPaymentSql = "INSERT INTO PAYMENTS(bankId, userId, amount)"
                + " VALUES(1, 2, 1.87), (2, 1, 3.41)";
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
