package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;

import java.util.List;

public class MySqlBankRepository implements BankRepository {
    @Override
    public List<Bank> findAll(String filename) {
        //TODO: connect database, Select*from BANKS, iterate ResultSet and add its results to Bank and return it
        return null;
    }

    @Override
    public Bank find(int id) {
        //TODO: return ONLY selected id
        return null;
    }

    @Override
    public List<Bank> findByName(String filename, String name) {
        //TODO: return ONLY selected name
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
