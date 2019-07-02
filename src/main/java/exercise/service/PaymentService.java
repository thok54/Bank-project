package exercise.service;

import exercise.dto.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> process();

    Payment find(int n);

    List<Payment> findAllByBankId(int id);

    void store(Payment payment);

    void update(int id, Payment payment);

    void delete(int id);
}