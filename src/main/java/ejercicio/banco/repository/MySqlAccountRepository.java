package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Account;

import java.util.List;

public class MySqlAccountRepository implements AccountRepository{
    @Override
    public List<Account> findAll(String filename) {
        return null;
    }

    @Override
    public Account find(int id) {
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
