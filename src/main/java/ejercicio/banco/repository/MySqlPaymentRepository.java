package ejercicio.banco.repository;

import ejercicio.banco.dto.Payment;

import java.util.List;

public class MySqlPaymentRepository implements PaymentRepository {
    @Override
    public List<Payment> findAll(String filename) {
        //TODO: connect database, Select*from BANKS, iterate ResultSet and add its results to Bank and return it
        return null;
    }

    @Override
    public Payment find(int id) {
        //TODO: return ONLY selected id
        return null;
    }

    @Override
    public List<Payment> findById(String filename, int id) {
        //TODO: return ONLY selected id
        return null;
    }

    @Override
    public void store(Payment payment) {

    }

    @Override
    public void update(int id, Payment payment) {

    }

    @Override
    public void delete(int id) {

    }
}
