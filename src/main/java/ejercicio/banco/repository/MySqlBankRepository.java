package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MySqlBankRepository implements BankRepository {

    private static final String FILENAME = "bank_project";

    private DataBaseUtil dataBaseUtil;

    public MySqlBankRepository() {
        this.dataBaseUtil = new DataBaseUtil();
    }

    @Override
    public List<Bank> findAll(String filename) {
        List<Bank> banks = new ArrayList();

        Connection con = dataBaseUtil.startConnection(filename);
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
        Connection con = dataBaseUtil.startConnection(FILENAME);

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
    public List<Bank> findByName(String filename, String name) {
        List<Bank> banks = new ArrayList();
        Connection con = dataBaseUtil.startConnection(filename);

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

    }

    @Override
    public void update(int id, Bank bank) {

    }

    @Override
    public void delete(int id) {

    }
}
