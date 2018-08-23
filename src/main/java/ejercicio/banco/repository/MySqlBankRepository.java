package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Bank;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MySqlBankRepository implements BankRepository {
    @Override
    public List<Bank> findAll(String filename) throws FileNotFoundException {
        return null;
    }

    @Override
    public Bank find(int id) throws FileNotFoundException {
        return null;
    }

    @Override
    public void store(Bank bank) throws IOException {

    }

    @Override
    public void update(int id, Bank bank) throws FileNotFoundException {

    }

    @Override
    public void delete(int id) throws FileNotFoundException {

    }
}
