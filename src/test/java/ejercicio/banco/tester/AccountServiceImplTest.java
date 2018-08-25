package test.java.ejercicio.banco.tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.repository.CsvAccountRepository;
import main.java.ejercicio.banco.service.AccountService;
import main.java.ejercicio.banco.service.AccountServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;


//Tests if AccountServiceImpl is generating Accounts properly from files
public class AccountServiceImplTest {

    @Test
    public void manage() throws FileNotFoundException {

        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.processAccounts(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        assertTrue("Not getting the right Address", accounts.get(1).getMoney() == 0.00);
    }
}