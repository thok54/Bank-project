package exercise.service;

import exercise.dto.Account;

import java.util.List;

public interface AccountService {

    List<Account> process();

    Account find(int accountId);

    List<Account> findAllByName(String name);

    void store(Account acc);

    void update(int id, Account acc);

    void delete(int id);

    void reset(Account acc);
}
