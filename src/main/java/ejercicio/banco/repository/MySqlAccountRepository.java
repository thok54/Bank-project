package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;
import ejercicio.banco.util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlAccountRepository implements AccountRepository {

    private static final String FILENAME = "bank_project";

    @Override
    public List<Account> findAll(String filename) {
        List<Account> accounts = new ArrayList();

        Connection con = DataBaseUtil.startConnection(filename);

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (con != null) {
                DataBaseUtil.closeConections(con);
            }
        }
        return accounts;
    }

    @Override
    public Account find(int id) {

        Connection con = DataBaseUtil.startConnection(FILENAME);

        try {
            //Reads ACCOUNTS table, returning results
            PreparedStatement pstmt = con.prepareStatement("select * from ACCOUNTS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer currentId = rs.getInt("id");
                if (id == currentId) {
                    String name = rs.getString("name");
                    Float money = rs.getFloat("money");
                    String iban = rs.getString("iban");
                    Account account = new Account(id, name, money, iban);
                    return account;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                DataBaseUtil.closeConections(con);
            }
        }
        return null;
    }

    @Override
    public List<Account> findByName(String filename, String name) {
        List<Account> accounts = new ArrayList();

        Connection con = DataBaseUtil.startConnection(filename);

        try {
            //Reads ACCOUNTS table, returning results
            PreparedStatement pstmt = con.prepareStatement("select * from ACCOUNTS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String currentName = rs.getString("name");
                if (name.equals(currentName)) {
                    Integer id = rs.getInt("id");
                    Float money = rs.getFloat("money");
                    String iban = rs.getString("iban");
                    Account account = new Account(id, name, money, iban);
                    accounts.add(account);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (con != null) {
                DataBaseUtil.closeConections(con);
            }
        }
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
