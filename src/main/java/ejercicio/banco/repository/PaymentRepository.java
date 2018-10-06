package ejercicio.banco.repository;

import ejercicio.banco.dto.Payment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll(String filename);

    Payment find(int id);

    //Prints storing
    void store(Payment payment);

    //Prints updating
    void update(int id, Payment payment);

    //Prints "deleting"
    void delete(int id);
}
