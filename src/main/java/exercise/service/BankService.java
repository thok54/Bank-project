package exercise.service;

import exercise.dto.Bank;

import java.util.List;


public interface BankService {
    List<Bank> process();

    Bank find(int n);

    List<Bank> findAllByName(String name);

    void store(Bank bank);

    void update(int id, Bank bank);

    void delete(int id);
}