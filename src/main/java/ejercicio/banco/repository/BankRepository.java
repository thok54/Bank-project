package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;

import java.util.List;

public interface BankRepository {
    List<Bank> findAll(String filename);

    Bank find(int id);

    List<Bank> findByName(String filename, String name);

    void store(Bank bank);

    void update(int id, Bank bank);

    void delete(int id);
}
