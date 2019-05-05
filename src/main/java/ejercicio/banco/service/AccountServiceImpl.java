package ejercicio.banco.service;

import ejercicio.banco.dto.Account;
import ejercicio.banco.repository.AccountNotFoundException;
import ejercicio.banco.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public List<Account> processAccounts() {
        return repository.findAll();
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
    public List<Account> findAccounts(String name) {
        return repository.findByName(name);
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
