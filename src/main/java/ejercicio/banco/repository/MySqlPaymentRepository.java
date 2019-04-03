package ejercicio.banco.repository;

import ejercicio.banco.dto.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static ejercicio.banco.util.DataBaseUtil.closeConections;
import static ejercicio.banco.util.DataBaseUtil.startConnection;

public class MySqlPaymentRepository implements PaymentRepository {

    private static final String FILENAME = "bank_project";

    @Override
    public List<Payment> findAll(String filename) {
        List<Payment> payments = new ArrayList();

        Connection con = startConnection(filename);

        try {
            //Reads PAYMENTS table, returning results
            PreparedStatement pstmt = con.prepareStatement("select * from PAYMENTS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer bankId = rs.getInt("bankId");
                Integer userId = rs.getInt("userId");
                Float amount = rs.getFloat("amount");
                Payment payment = new Payment(id, bankId, userId, amount);
                payments.add(payment);
            }

            closeConections(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public Payment find(int id) {

        Connection con = startConnection(FILENAME);

        try {
            //Reads PAYMENTS table, returning results
            PreparedStatement pstmt = con.prepareStatement("select * from PAYMENTS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer currentId = rs.getInt("id");
                if (id == currentId) {
                    Integer bankId = rs.getInt("bankId");
                    Integer userId = rs.getInt("userId");
                    Float amount = rs.getFloat("amount");
                    Payment payment = new Payment(id, bankId, userId, amount);
                    closeConections(con);
                    return payment;
                }
            }
            closeConections(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Payment> findByBankId(String filename, int bankId) {
        List<Payment> payments = new ArrayList();

        Connection con = startConnection(filename);

        try {
            //Reads PAYMENTS table, returning results
            PreparedStatement pstmt = con.prepareStatement("select * from PAYMENTS");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer currentId = rs.getInt("bankId");
                if (bankId == currentId) {
                    Integer id = rs.getInt("id");
                    Integer userId = rs.getInt("userId");
                    Float amount = rs.getFloat("amount");
                    Payment payment = new Payment(id, bankId, userId, amount);
                    payments.add(payment);
                }
            }
            closeConections(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public void store(Payment payment) {

    }

    @Override
    public void update(int id, Payment payment) {

    }

    @Override
    public void delete(int id) {

    }
}
