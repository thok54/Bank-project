package ejercicio.banco.service;

import ejercicio.banco.dto.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> process();

    Payment find(int n);

    List<Payment> findByBankId(int id);

    void store(Payment payment);

    void update(int id, Payment payment);

    void delete(int id);
}