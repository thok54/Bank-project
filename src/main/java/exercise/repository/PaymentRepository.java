package exercise.repository;

import exercise.dto.Payment;

import java.util.List;

public interface PaymentRepository {

    List<Payment> findAll();

    Payment find(int id);

    List<Payment> findAllByBankId(int id);

    void store(Payment payment);

    void update(int id, Payment payment);

    void delete(int id);
}
