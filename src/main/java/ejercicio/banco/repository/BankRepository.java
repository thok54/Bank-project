package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;

import java.sql.SQLException;
import java.util.List;

public interface BankRepository {
    List<Bank> findAll();

    Bank find(int id) throws SQLException;

    List<Bank> findByName(String name);
  
    void store(Bank bank);

    void update(int id, Bank bank);

    void delete(int id);
}
