package ejercicio.banco.service;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.repository.BankRepository;

import java.util.List;

public class BankServiceImpl implements BankService{

    private BankRepository repository;

    public BankServiceImpl(BankRepository repository) {
        this.repository = repository;
    }

    public Bank processBank(String filename) throws IndexOutOfBoundsException{
        if (filename == null) {
            throw new IllegalArgumentException("Filename must not be null");
        }
        Bank bank;
        List<Bank> banks = repository.findAll(filename);
        if(banks.size() < 1){
            return null;
        }
        bank = banks.get(0);
        return bank;
    }



    // Find bank
    public Bank findBank(int n) {
        Bank bank = null;
        bank = repository.find(n);
        return bank;
    }

    // Find banks by string
    public List<Bank> findBanks(String filename, String name) {
        return repository.findByName(filename, name);
    }


    public void storeBank(Bank bank){
        repository.store(bank);
    }

    public void updateBank(int id, Bank bank){
        repository.update(id,bank);
    }

    public void deleteBank(int id){
        repository.delete(id);
    }

}
