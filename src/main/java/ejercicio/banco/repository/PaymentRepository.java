package ejercicio.banco.repository;

import ejercicio.banco.dto.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll();

    Payment find(int id) throws SQLException;

    List<Payment> findAllByBankId(int id);

    void store(Payment payment);

    void update(int id, Payment payment);

    void delete(int id);
}
