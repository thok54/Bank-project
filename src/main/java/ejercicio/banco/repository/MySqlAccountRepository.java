package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;
import ejercicio.banco.dto.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ejercicio.banco.util.DataBaseUtil.closeConections;
import static ejercicio.banco.util.DataBaseUtil.startConnection;

public class MySqlAccountRepository implements AccountRepository {
    @Override
    public List<Account> findAll(String filename) throws SQLException {
        List<Account> accounts = new ArrayList();

        Connection con = startConnection();

        //Reads ACCOUNTS table, returning results
        PreparedStatement pstmt = con.prepareStatement("select * from ACCOUNTS");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            Float money = rs.getFloat("money");
            String iban = rs.getString("iban");
            Account account = new Account(id, name, money, iban);
            accounts.add(account);
        }

        closeConections(con);
        return accounts;
    }

    @Override
    public Account find(int id) throws SQLException {
        Connection con = startConnection();

        //Reads ACCOUNTS table, returning results
        PreparedStatement pstmt = con.prepareStatement("select * from ACCOUNTS");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Integer currentId = rs.getInt("id");
            if(id==currentId) {
                String name = rs.getString("name");
                Float money = rs.getFloat("money");
                String iban = rs.getString("iban");
                Account account = new Account(id, name, money, iban);
                closeConections(con);
                return account;
            }
        }
        closeConections(con);
        return null;
    }

    @Override
    public List<Account> findByName(String filename, String name) throws SQLException {
        List<Account> accounts = new ArrayList();
        Connection con = startConnection();

        //Reads ACCOUNTS table, returning results
        PreparedStatement pstmt = con.prepareStatement("select * from ACCOUNTS");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String currentName = rs.getString("name");
            if(name.equals(currentName)) {
                Integer id = rs.getInt("id");
                Float money = rs.getFloat("money");
                String iban = rs.getString("iban");
                Account account = new Account(id, name, money, iban);
                accounts.add(account);
            }
        }
        closeConections(con);
        return accounts;
    }

    @Override
    public void store(Account account) {

    }

    @Override
    public void update(int id, Account account) {

    }

    @Override
    public void delete(int id) {

    }
}
