package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Account;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AccountRepository {

    List<Account> findAll(String filename) throws FileNotFoundException;

    Account find(int id) throws FileNotFoundException;

    //Prints storing
    void store(Account account) throws IOException;

    //Prints updating
    void update(int id, Account account) throws FileNotFoundException;

    //Prints "deleting"
    void delete(int id) throws FileNotFoundException;
}
