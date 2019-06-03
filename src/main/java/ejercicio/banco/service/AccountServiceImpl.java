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
    public List<Account> process() {
        return repository.findAll();
    }

    @Override
    public Account find(int accountId) {
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

    @Override
    public List<Account> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void store(Account acc) {
        repository.store(acc);
    }

    @Override
    public void update(int id, Account acc) {
        repository.update(id, acc);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void reset(Account acc) {
        repository.reset(acc);
    }

}
