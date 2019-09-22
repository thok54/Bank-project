package exercise.service;


import exercise.dto.Payment;
import exercise.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Override
    public List<Payment> process(/*Bank bestBank*/) {
        return repository.findAll();
    }

    @Override
    public Payment find(int n) {
        return repository.find(n);
    }

    @Override
    public List<Payment> findAllByBankId(int id) {
        return repository.findAllByBankId(id);
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
