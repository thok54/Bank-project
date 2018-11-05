package ejercicio.banco.repository;

import ejercicio.banco.dto.Payment;

import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll(String filename);

    Payment find(int id);

    List<Payment> findById(String filename, int id);

    //Prints storing
    void store(Payment payment);

    //Prints updating
    void update(int id, Payment payment);

    //Prints "deleting"
    void delete(int id);
}
