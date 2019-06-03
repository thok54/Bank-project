package ejercicio.banco.service;


import ejercicio.banco.dto.Payment;
import ejercicio.banco.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Override
    public List<Payment> process(/*Bank bestBank*/) {
        List<Payment> payments = repository.findAll();
        return payments;
    }

    @Override
    public Payment find(int n) {
        Payment payment = null;
        try {
            payment = repository.find(n);
        } catch (Exception e) {
            e.printStackTrace();
            payment = null;
        }
        return payment;
    }

    @Override
    public List<Payment> findByName(String name) {
        int id = Integer.parseInt(name);
        return repository.findByBankId(id);
    }

    @Override
    public void store(Payment payment) {
        repository.store(payment);
    }

    @Override
    public void update(int id, Payment payment) {
        repository.update(id, payment);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

}
