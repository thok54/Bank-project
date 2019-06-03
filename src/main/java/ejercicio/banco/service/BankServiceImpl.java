package ejercicio.banco.service;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository repository;

    @Override
    public List<Bank> process() throws IndexOutOfBoundsException {
        Bank bank;
        List<Bank> banks = repository.findAll();
        return banks;
    }

    @Override
    public Bank find(int n) {
        Bank bank = null;
        try {
            bank = repository.find(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank;
    }

    @Override
    public List<Bank> findByName(String name) {
        return repository.findByName(name);
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
