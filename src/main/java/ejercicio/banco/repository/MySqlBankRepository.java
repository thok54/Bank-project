package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySqlBankRepository implements BankRepository {

    private DataBaseUtil dataBaseUtil;

    public MySqlBankRepository() {
        this.dataBaseUtil = new DataBaseUtil();
    }

    @Override
    public List<Bank> findAll() {
        List<Bank> banks = new ArrayList();

        Connection con = dataBaseUtil.startConnection();
        try {
            //Reads BANKS table, returning results
            PreparedStatement pstmt = con.prepareStatement("select * from BANKS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                Bank bank = new Bank(id, name, address);
                banks.add(bank);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return banks;
    }

    @Override
    public Bank find(int id) {
        Connection con = dataBaseUtil.startConnection();

        try {
            //Reads BANKS table, returning results
            PreparedStatement pstmt = con.prepareStatement("select * from BANKS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer currentId = rs.getInt("id");
                if (id == currentId) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    Bank bank = new Bank(id, name, address);
                    return bank;
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
    public List<Bank> findByName(String name) {
        List<Bank> banks = new ArrayList();
        Connection con = dataBaseUtil.startConnection();

        try {
            //Reads BANKS table, returning results
            PreparedStatement pstmt = con.prepareStatement("select * from BANKS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String currentName = rs.getString("name");
                if (name.equals(currentName)) {
                    Integer id = rs.getInt("id");
                    String address = rs.getString("address");
                    Bank bank = new Bank(id, name, address);
                    banks.add(bank);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return banks;
    }


    @Override
    public void store(Bank bank) {
        Connection con = dataBaseUtil.startConnection();
        try{
            String name = bank.getName();
            String address = bank.getAddress();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO BANKS (name, address) VALUES ("+name
                    +", " +address+")");
            ResultSet rs = pstmt.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dataBaseUtil.closeConections(con);
        }
    }

    @Override
    public void update(int id, Bank bank) {
        Connection con = dataBaseUtil.startConnection();
        try{
            String name = bank.getName();
            String address = bank.getAddress();
            PreparedStatement pstmt = con.prepareStatement("UPDATE BANKS SET name = "+name+", address = "+address+
                    " WHERE id = "+bank.getId());
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
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM BANKS WHERE id = "+id);
            ResultSet rs = pstmt.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dataBaseUtil.closeConections(con);
        }

    }
}
