package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ejercicio.banco.util.DataBaseUtil.startConnection;
import static ejercicio.banco.util.DataBaseUtil.closeConections;

public class MySqlBankRepository implements BankRepository {
    @Override
    public List<Bank> findAll(String filename) throws SQLException {
        List<Bank> banks = new ArrayList();

        Connection con = startConnection();

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

        closeConections(con);
        return banks;
    }

    @Override
    public Bank find(int id) throws SQLException {
        Connection con = startConnection();

        //Reads BANKS table, returning results
        PreparedStatement pstmt = con.prepareStatement("select * from BANKS");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Integer currentId = rs.getInt("id");
            if(id==currentId) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                Bank bank = new Bank(id, name, address);
                closeConections(con);
                return bank;
            }
        }
        closeConections(con);
        return null;
    }

    @Override
    public List<Bank> findByName(String filename, String name) throws SQLException {
        List<Bank> banks = new ArrayList();
        Connection con = startConnection();

        //Reads BANKS table, returning results
        PreparedStatement pstmt = con.prepareStatement("select * from BANKS");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String currentName = rs.getString("name");
            if(name.equals(currentName)) {
                Integer id = rs.getInt("id");
                String address = rs.getString("address");
                Bank bank = new Bank(id, name, address);
                banks.add(bank);
            }
        }
        closeConections(con);
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
