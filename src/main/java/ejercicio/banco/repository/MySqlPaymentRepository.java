package ejercicio.banco.repository;

import ejercicio.banco.dto.Payment;
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
public class MySqlPaymentRepository implements PaymentRepository {

    @Autowired
    private DataBaseUtil dataBaseUtil;

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList();

        Connection con = dataBaseUtil.startConnection();

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return payments;
    }

    @Override
    public Payment find(int id) {

        Connection con = dataBaseUtil.startConnection();

        try {
            PreparedStatement pstmt = con.prepareStatement("select * from PAYMENTS where id = " + id);
            ResultSet rs = pstmt.executeQuery();
            //if (rs.next()) {
                Integer bankId = rs.getInt("bankId");
                Integer userId = rs.getInt("userId");
                Float amount = rs.getFloat("amount");
                Payment payment = new Payment(id, bankId, userId, amount);
                return payment;
            //}throw new EntityNotFoundException(String.format("Payment with ID = %d does not exist", id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return null;
    }

    @Override
    public List<Payment> findByBankId(int bankId) {
        List<Payment> payments = new ArrayList();

        Connection con = dataBaseUtil.startConnection();

        try {
            PreparedStatement pstmt = con.prepareStatement("select * from PAYMENTS where bankId = " + bankId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer userId = rs.getInt("userId");
                Float amount = rs.getFloat("amount");
                Payment payment = new Payment(id, bankId, userId, amount);
                payments.add(payment);
            }
            return payments;
            //throw new EntityNotFoundException(String.format("Payment with bankId = %d does not exist", bankId));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return null;
    }

    @Override
    public void store(Payment payment) {
        Connection con = dataBaseUtil.startConnection();
        try {
            Integer bankId = payment.getBankId();
            Integer userId = payment.getUserId();
            Float amount = payment.getAmount();

            String query = String.format("INSERT INTO PAYMENTS (bankId, userId, amount) VALUES (%d, %d, %f)", bankId, userId, amount);
            PreparedStatement pstmt = con.prepareStatement(query);
            int rs = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }

    @Override
    public void update(int id, Payment payment) {
        Connection con = dataBaseUtil.startConnection();
        try {
            Integer bankId = payment.getBankId();
            Integer userId = payment.getUserId();
            Float amount = payment.getAmount();

            String query = String.format("UPDATE PAYMENTS SET bankId = %d, userID = %d, amount = %f WHERE id = %d", bankId, userId, amount, id);
            PreparedStatement pstmt = con.prepareStatement(query);
            int rs = pstmt.executeUpdate();

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
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM PAYMENTS WHERE id = " + id);
            int rs = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }
}
