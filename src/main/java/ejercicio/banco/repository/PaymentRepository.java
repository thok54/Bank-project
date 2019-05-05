package ejercicio.banco.repository;

import ejercicio.banco.dto.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll();

    Payment find(int id) throws SQLException;

    List<Payment> findByBankId(int id);

    //Prints storing
    void store(Payment payment);

    //Prints updating
    void update(int id, Payment payment);

    //Prints "deleting"
    void delete(int id);
}
