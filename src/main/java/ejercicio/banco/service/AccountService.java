package ejercicio.banco.service;

import ejercicio.banco.dto.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {

    List<Account> process();

    Account find(int accountId);

    List<Account> findByName(String name);

    void store(Account acc);

    void update(int id, Account acc);

    void delete(int id);
}
