package test.java.ejercicio.banco.repository;
import ejercicio.banco.dto.Account;
import ejercicio.banco.repository.CsvAccountRepository;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.List;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CsvAccountRepositoryTest {

    private CsvAccountRepository repository;

    @Before
    public void setup() {
        this.repository = new CsvAccountRepository();
    }

    @Test
    public void findAllTest(){
        //When
        List<Account> accounts = repository.findAll(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        //Then
        assertTrue("Find all not processing the correct accounts", accounts.get(1).getMoney() == 0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findAllThrowsExceptionWhenFileNotFoundTest(){
        //Given
        String accountFileTest = String.join(File.separator, "src", "test", "resources", "csv", "mentTd2fd2est.csv");
        //When
        repository.findAll(accountFileTest);
    }

    @Test
    public void findTest(){
        //When
        Account account = repository.find(20);
        assertNull(account);
    }

    @Test
    public void storeAccountTest(){
        //When
        List<Account> accounts = repository.findAll(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        Account account = accounts.get(0);
        float money = account.getMoney();
        repository.store(account);
        //Then
        assertTrue("Account should remain same", account.getMoney()==money);
    }

    @Test
    public void updateAccountTest(){
        //When
        List<Account> accounts = repository.findAll(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        Account account = accounts.get(0);
        float money = account.getMoney();
        repository.update(0,account);
        //Then
        assertTrue("Account should remain same", account.getMoney()==money);
    }


    @Test
    public void deleteAccountTest(){
        //When
        List<Account> accounts = repository.findAll(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        Account account = accounts.get(0);
        float money = account.getMoney();
        repository.delete(0);
        //Then
        assertTrue("Account should remain same", account.getMoney()==money);
    }



}
