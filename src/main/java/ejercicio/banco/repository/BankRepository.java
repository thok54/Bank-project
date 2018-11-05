package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;

import java.util.List;

public interface BankRepository {
    List<Bank> findAll(String filename);

    Bank find(int id);

    public List<Bank> findByName(String filename, String name);

    //Prints storing
    void store(Bank bank);

    //Prints updating
    void update(int id, Bank bank);

    //Prints "deleting"
    void delete(int id);
}
