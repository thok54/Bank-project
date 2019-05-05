package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    Account find(int id) throws SQLException;

    List<Account> findByName(String name);

    //Prints storing
    void store(Account account);

    //Prints updating
    void update(int id, Account account);

    //Prints "deleting"
    void delete(int id);
}
