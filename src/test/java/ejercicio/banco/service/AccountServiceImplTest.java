package ejercicio.banco.service;

import java.io.File;
import java.util.List;

import ejercicio.banco.dto.Account;
import ejercicio.banco.repository.CsvAccountRepository;
import ejercicio.banco.service.AccountService;
import ejercicio.banco.service.AccountServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;


//Tests if AccountServiceImpl is generating Accounts properly from files
public class AccountServiceImplTest {

    @Test
    public void manage() {

        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.processAccounts(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        assertTrue("Not getting the right Address", accounts.get(1).getMoney() == 0.00);
    }
}