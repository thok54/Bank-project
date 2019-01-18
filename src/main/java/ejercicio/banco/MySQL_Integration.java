package ejercicio.banco;

import java.sql.*;

//TODO: Not sure where to do this
public class MySQL_Integration {
    /*
    //Register driver
    Class.forName("com.mysql.cj.jdbc.Driver");


    //Creates Connection
    Connection con = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/myDb", "user1", "pass");

    public MySQL_Integration() throws SQLException {
    }

    CREATE DATABASE myDb;
    CREATE USER 'user1' IDENTIFIED BY 'pass';
    GRANT ALL on myDb.* TO 'user1';


    //Statements
    Statement stmt = con.createStatement();


    //Creates Tables
    String bankTableSql = "CREATE TABLE IF NOT EXISTS BANKS"
            + "(id int NOT NULL PRIMARY KEY, name varchar(20), address varchar(50))";

    stmt.execute(bankTableSql);

    String accountTableSql =  "CREATE TABLE IF NOT EXISTS ACCOUNTS"
            + "(id int NOT NULL PRIMARY KEY, name varchar(20), money float, iban varchar(20))";
    stmt.execute(accountTableSql);

    String paymentTableSql =  "CREATE TABLE IF NOT EXISTS PAYMENTS"
            + "(id int NOT NULL PRIMARY KEY, bank_id INT, user_id INT, amount float, FOREIGN KEY(bank_id) REFERENCES BANKS(id), FOREIGN KEY(user_id) REFERENCES ACCOUNTS(id))";
    stmt.execute(paymentTableSql);


    //Insert values to Tables
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
    ResultSet resultSet = stmt.executeQuery(selectBankSql);

    String selectPaymentSql = "SELECT * FROM ACCOUNTS";
    ResultSet resultSet = stmt.executeQuery(selectPaymentSql);

    String selectPaymentSql = "SELECT * FROM PAYMENTS";
    ResultSet resultSet = stmt.executeQuery(selectPaymentSql);
*/
}
