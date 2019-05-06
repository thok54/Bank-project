package ejercicio.banco.service;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> process();

    void fileWriter(String filename, Bank bestBank);

    Payment find(int n);

    List<Payment> findByName(String name);

    void store(Payment payment);

    void update(int id, Payment payment);

    void delete(int id);
}