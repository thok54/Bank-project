package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;
import ejercicio.banco.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySqlAccountRepository implements AccountRepository {

    @Autowired
    private DataBaseUtil dataBaseUtil;

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList();

        Connection con = dataBaseUtil.startConnection();

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
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return accounts;
    }

    @Override
    public Account find(int id) {

        Connection con = dataBaseUtil.startConnection();

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
            dataBaseUtil.closeConections(con);
        }
        return null;
    }

    @Override
    public List<Account> findByName(String name) {
        List<Account> accounts = new ArrayList();

        Connection con = dataBaseUtil.startConnection();

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
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return accounts;
    }

    @Override
    public void store(Account account) {
        Connection con = dataBaseUtil.startConnection();
        try {
            String name = account.getName();
            Float money = account.getMoney();
            String iban = account.getIban();

            String query = String.format("INSERT INTO ACCOUNTS (name, money, iban) VALUES (\"%s\", %f, \"%s\")", name, money, iban);
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }

    }

    @Override
    public void update(int id, Account account) {
        Connection con = dataBaseUtil.startConnection();
        try {
            String name = account.getName();
            Float money = account.getMoney();
            String iban = account.getIban();

            String query = String.format("UPDATE ACCOUNTS SET name = \"%s\", money = %f, iban = \"%s\" WHERE id = %d", name, money, iban, id);
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }

    @Override
    public void delete(int id) {
        Connection con = dataBaseUtil.startConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM ACCOUNTS WHERE id = " + id);
            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }


    @Override
    public void reset(Account acc) {
        Connection con = dataBaseUtil.startConnection();
        try {
            int id = acc.getId();

            String query1 = String.format("UPDATE ACCOUNTS SET money = 0 WHERE id = " + id);
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.executeUpdate();

            String query2 = String.format("DELETE FROM PAYMENTS WHERE userId = " + id);
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            ResultSet rs = pstmt2.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }
}
