package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Payment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MySqlPaymentRepository implements PaymentRepository {
    @Override
    public List<Payment> findAll(String filename) {
        return null;
    }

    @Override
    public Payment find(int id)  {
        return null;
    }

    @Override
    public void store(Payment payment)  {

    }

    @Override
    public void update(int id, Payment payment) {

    }

    @Override
    public void delete(int id)  {

    }
}
