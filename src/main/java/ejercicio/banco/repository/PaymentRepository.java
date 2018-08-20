package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Payment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll(String filename) throws FileNotFoundException;

    Payment find(int id) throws FileNotFoundException;

    //Prints storing
    void store(Payment payment) throws IOException;

    //Prints updating
    void update(int id, Payment payment) throws FileNotFoundException;

    //Prints "deleting"
    void delete(int id) throws FileNotFoundException;
}
