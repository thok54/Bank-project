package test.java.ejercicio.banco.tester;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.repository.CsvAccountRepository;
import main.java.ejercicio.banco.service.AccountService;
import main.java.ejercicio.banco.service.AccountServiceImpl;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CsvAccountRepositoryTest {

    @Test
    public void findAllTest(){
        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.processAccounts(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        //assertEquals("Find all not processing the correct accounts", accounts.get(1).getMoney(), 0.00);
        assertTrue("Find all not processing the correct accounts", accounts.get(1).getMoney() == 0.00);
    }

    @Test
    public void findTest(){
        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        Account account = ((AccountServiceImpl) accountServiceTest).findAccount(20);
        assertNull(account);

    }

    @Test
    public void otherTests(){
        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.processAccounts(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));

        Account account = accounts.get(0);
        float money = account.getMoney();

        ((AccountServiceImpl) accountServiceTest).storeAccount(account);
        ((AccountServiceImpl) accountServiceTest).updateAccount(0, account);
        ((AccountServiceImpl) accountServiceTest).deleteAccount(0);

        assertTrue("Account should remain same", account.getMoney()==money);
        //assertEquals("These commands should not modify accounts", account.getMoney(), money);

    }



}
