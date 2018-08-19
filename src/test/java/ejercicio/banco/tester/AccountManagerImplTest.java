package test.java.ejercicio.banco.tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.service.AccountManager;
import main.java.ejercicio.banco.service.AccountManagerImpl;
import org.junit.Test;

import static org.junit.Assert.*;


//Tests if AccountManagerImpl is generating Accounts properly from files
public class AccountManagerImplTest {

    @Test
    public void manage() throws FileNotFoundException {

        AccountManager accountManagerTest = new AccountManagerImpl();
        List<Account> accounts = accountManagerTest.manage(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        assertTrue("Not getting the right Address", accounts.get(1).getMoney() == 0.00);
    }
}