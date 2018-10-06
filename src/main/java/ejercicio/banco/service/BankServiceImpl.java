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
        Bank bank;
        List<Bank> banks = repository.findAll(filename);
        bank = banks.get(0);
        return bank;
    }



    // Find bank
    public Bank findBank(int n) {
        // Creates list of Accounts
        Bank bank = null;
        bank = repository.find(n);
        return bank;
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
