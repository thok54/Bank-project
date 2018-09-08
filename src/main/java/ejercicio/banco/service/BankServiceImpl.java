package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.repository.BankRepository;

import java.io.FileNotFoundException;
import java.util.List;

public class BankServiceImpl implements BankService {

    private BankRepository repository;

    public BankServiceImpl(BankRepository repository) {
        this.repository = repository;
    }

    public Bank processBank(String filename) {
        List<Bank> banks = repository.findAll(filename);
        return banks.get(0);
    }



    // Find bank
    public Bank findBank(int n) {
        // Creates list of Accounts
        Bank bank = null;
        try {
            bank = repository.find(n);
        }
        catch(Exception e){
            bank = null;
        }
        return bank;
    }
}
