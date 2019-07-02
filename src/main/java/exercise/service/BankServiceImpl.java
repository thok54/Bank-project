package exercise.service;

import exercise.dto.Bank;
import exercise.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository repository;

    @Override
    public List<Bank> process() throws IndexOutOfBoundsException {
        return repository.findAll();
    }

    @Override
    public Bank find(int n) {
        return repository.find(n);
    }

    @Override
    public List<Bank> findAllByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public void store(Bank bank) {
        repository.store(bank);
    }

    @Override
    public void update(int id, Bank bank) {
        repository.update(id, bank);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

}
