package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;

import java.util.List;

public class MySqlBankRepository implements BankRepository {
    @Override
    public List<Bank> findAll(String filename) {
        return null;
    }

    @Override
    public Bank find(int id) {
        return null;
    }

    @Override
    public List<Bank> findByName(String filename, String name) {
        return null;
    }


    @Override
    public void store(Bank bank) {

    }

    @Override
    public void update(int id, Bank bank) {

    }

    @Override
    public void delete(int id) {

    }
}
