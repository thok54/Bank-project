package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AccountRepository {

    List<Account> findAll(String filename);

    Account find(int id);

    //Prints storing
    void store(Account account);

    //Prints updating
    void update(int id, Account account);

    //Prints "deleting"
    void delete(int id);
}
