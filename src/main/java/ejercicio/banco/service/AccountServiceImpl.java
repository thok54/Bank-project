package ejercicio.banco.service;

import ejercicio.banco.dto.Account;
import ejercicio.banco.repository.AccountNotFoundException;
import ejercicio.banco.repository.AccountRepository;

import java.util.List;


public class AccountServiceImpl implements AccountService {

    private AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    // Process user
    public List<Account> processAccounts(String filename) {
        // Creates list of Accounts
        List<Account> accounts = repository.findAll(filename);
        return accounts;
    }

    // Find user
    public Account findAccount(int n) {
        // Creates list of Accounts
        Account account = null;
        try {
            account = repository.find(n);
        }
        catch(AccountNotFoundException e){
            e.printStackTrace();
            account = null;
        }
        return account;
    }


    public void storeAccount(Account acc){
        repository.store(acc);
    }

    public void updateAccount(int id, Account acc){
        repository.update(id,acc);
    }

    public void deleteAccount(int id){
        repository.delete(id);
    }

}
