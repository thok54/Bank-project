package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Bank;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BankRepository {
    List<Bank> findAll(String filename) throws FileNotFoundException;

    Bank find(int id) throws FileNotFoundException;

    //Prints storing
    void store(Bank bank) throws IOException;

    //Prints updating
    void update(int id, Bank bank) throws FileNotFoundException;

    //Prints "deleting"
    void delete(int id) throws FileNotFoundException;
}
