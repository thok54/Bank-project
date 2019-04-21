package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;
import ejercicio.banco.util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlAccountRepository implements AccountRepository {

    private DataBaseUtil dataBaseUtil;

    public MySqlAccountRepository() {
        this.dataBaseUtil = new DataBaseUtil();
    }

    @Override
    public List<Account> findAll(String filename) {
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
    public List<Account> findByName(String filename, String name) {
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
        try{
            String name = account.getName();
            Float money = account.getMoney();
            String iban = account.getIban();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO ACCOUNTS (name, money, iban) VALUES ("+name
                   +", " +money+", "+iban+")");
            ResultSet rs = pstmt.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dataBaseUtil.closeConections(con);
        }

    }

    @Override
    public void update(int id, Account account) {
        Connection con = dataBaseUtil.startConnection();
        try{
            String name = account.getName();
            Float money = account.getMoney();
            String iban = account.getIban();
            PreparedStatement pstmt = con.prepareStatement("UPDATE ACCOUNTS SET name = "+name+
                    ", money = "+money+", iban = "+iban+ " WHERE id = "+account.getId());
            ResultSet rs = pstmt.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dataBaseUtil.closeConections(con);
        }
    }

    @Override
    public void delete(int id) {
        Connection con = dataBaseUtil.startConnection();
        try{
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM ACCOUNTS WHERE id = "+id);
            ResultSet rs = pstmt.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dataBaseUtil.closeConections(con);
        }
    }
}
