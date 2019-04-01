package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountRepository {

    List<Account> findAll(String filename) throws SQLException;

    Account find(int id) throws SQLException;

    public List<Account> findByName(String filename, String name) throws SQLException;

    //Prints storing
    void store(Account account);

    //Prints updating
    void update(int id, Account account);

    //Prints "deleting"
    void delete(int id);
}
