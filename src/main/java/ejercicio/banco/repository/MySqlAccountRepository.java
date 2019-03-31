package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;

import java.util.List;

public class MySqlAccountRepository implements AccountRepository {
    @Override
    public List<Account> findAll(String filename) {
        //TODO: connect database, Select*from BANKS, iterate ResultSet and add its results to Bank and return it
        return null;
    }

    @Override
    public Account find(int id) {
        //TODO: return ONLY selected id
        return null;
    }

    @Override
    public List<Account> findByName(String filename, String name) {
        //TODO: return ONLY selected name
        return null;
    }

    @Override
    public void store(Account account) {

    }

    @Override
    public void update(int id, Account account) {

    }

    @Override
    public void delete(int id) {

    }
}
