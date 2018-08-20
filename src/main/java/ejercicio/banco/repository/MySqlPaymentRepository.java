package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Payment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MySqlPaymentRepository implements PaymentRepository {
    @Override
    public List<Payment> findAll(String filename) throws FileNotFoundException {
        return null;
    }

    @Override
    public Payment find(int id) throws FileNotFoundException {
        return null;
    }

    @Override
    public void store(Payment payment) throws IOException {

    }

    @Override
    public void update(int id, Payment payment) throws FileNotFoundException {

    }

    @Override
    public void delete(int id) throws FileNotFoundException {

    }
}
