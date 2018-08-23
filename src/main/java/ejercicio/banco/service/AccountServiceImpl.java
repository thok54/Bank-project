package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.repository.AccountRepository;

import java.io.FileNotFoundException;
import java.util.List;


public class AccountServiceImpl implements AccountService {

    private AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    // Process user
    public List<Account> manage(String filename) throws FileNotFoundException {
        // Creates list of Accounts
        List<Account> accounts = repository.findAll(filename);
        return accounts;
    }

}
