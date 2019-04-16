package ejercicio.banco.service;

import ejercicio.banco.dto.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {

    List<Account> processAccounts(String filename);
}
