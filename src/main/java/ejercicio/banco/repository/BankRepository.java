package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;

import java.sql.SQLException;
import java.util.List;

public interface BankRepository {
    List<Bank> findAll(String filename) throws SQLException;

    Bank find(int id) throws SQLException;

    List<Bank> findByName(String filename, String name) throws SQLException;
  
    void store(Bank bank);

    void update(int id, Bank bank);

    void delete(int id);
}
