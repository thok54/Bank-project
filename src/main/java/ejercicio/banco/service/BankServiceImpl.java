package ejercicio.banco.service;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.repository.BankRepository;

import java.sql.SQLException;
import java.util.List;

public class BankServiceImpl implements BankService {

    private BankRepository repository;

    public BankServiceImpl(BankRepository repository) {
        this.repository = repository;
    }

    public Bank processBank(String filename) throws IndexOutOfBoundsException {
        if (filename == null) {
            throw new IllegalArgumentException("Filename must not be null");
        }
        Bank bank;
        List<Bank> banks = null;
        try {
            banks = repository.findAll(filename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            bank = banks.get(0);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            bank = null;
        }
        return bank;
    }

    public Bank findBank(int n) {
        Bank bank = null;
        try {
            bank = repository.find(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank;
    }
  
    public List<Bank> findBanks(String filename, String name) {
        try {
            return repository.findByName(filename, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void storeBank(Bank bank) {
        repository.store(bank);
    }

    public void updateBank(int id, Bank bank) {
        repository.update(id, bank);
    }

    public void deleteBank(int id) {
        repository.delete(id);
    }

}
