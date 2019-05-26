package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    Account find(int id) throws SQLException;

    List<Account> findByName(String name);

    void store(Account account);

    void update(int id, Account account);

    void delete(int id);

    void reset(Account acc);
}
