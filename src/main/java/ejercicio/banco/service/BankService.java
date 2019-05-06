package ejercicio.banco.service;

import ejercicio.banco.dto.Bank;

import java.sql.SQLException;
import java.util.List;


public interface BankService {
    List<Bank> process();

    Bank find(int n);

    List<Bank> findByName(String name);

    void store(Bank bank);

    void update(int id, Bank bank);

    void delete(int id);
}