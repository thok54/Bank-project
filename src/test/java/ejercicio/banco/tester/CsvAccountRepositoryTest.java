package test.java.ejercicio.banco.tester;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.repository.CsvAccountRepository;
import main.java.ejercicio.banco.service.AccountService;
import main.java.ejercicio.banco.service.AccountServiceImpl;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CsvAccountRepositoryTest {

    @Test
    public void findAllTest(){
        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.processAccounts(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        assertTrue("Find all not processing the correct accounts", accounts.get(1).getMoney() == 0.00);
    }

    @Test
    public void findTest(){
        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        Account account = ((AccountServiceImpl) accountServiceTest).findAccount(20);
        assertNull(account);

    }



}
