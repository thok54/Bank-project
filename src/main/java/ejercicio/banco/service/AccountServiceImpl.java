package ejercicio.banco.service;

import ejercicio.banco.dto.Account;
import ejercicio.banco.repository.AccountNotFoundException;
import ejercicio.banco.repository.AccountRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountServiceImpl implements AccountService {

    private AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> processAccounts(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename must not be null");
        }

        try {
            return repository.findAll(filename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Find user
    public Account findAccount(int accountId) {
        // Creates list of Accounts
        Account account = null;
        try {
            account = repository.find(accountId);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
            account = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // Find accounts by string
    public List<Account> findAccounts(String filename, String name){
        try {
            return repository.findByName(filename, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void storeAccount(Account acc) {
        repository.store(acc);
    }

    public void updateAccount(int id, Account acc) {
        repository.update(id, acc);
    }

    public void deleteAccount(int id) {
        repository.delete(id);
    }

}
