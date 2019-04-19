package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;
import ejercicio.banco.util.DataBaseUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MySqlAccountRepositoryTest extends AbstractMySqlRepositoryTest {

    private Account account;
    private Account expectedAccount1 = new Account(1, "Peter",(float)3.00, "PIPIRANA87");
    private Account expectedAccount2 = new Account(2, "Aurelio", (float)8.49, "SATURN15STINKS");

    @Mock
    private DataBaseUtil util;

    @InjectMocks
    MySqlAccountRepository repository;


    @Test
    public void testFindAllReturnsFullList() throws SQLException {
        executeQuery(util, "select * from ACCOUNTS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedAccount1.getName()).thenReturn(expectedAccount2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedAccount1.getId()).thenReturn(expectedAccount2.getId());
        when(resultSet.getFloat("money")).thenReturn(expectedAccount1.getMoney()).thenReturn(expectedAccount2.getMoney());
        when(resultSet.getString("iban")).thenReturn(expectedAccount1.getIban()).thenReturn(expectedAccount2.getIban());

        List<Account> full = new ArrayList();

        full.add(expectedAccount1);
        full.add(expectedAccount2);
        full = repository.findAll("");
        assertFalse(full.isEmpty());
        assertEquals(2, full.size());
    }


    @Test
    public void testFindReturnsNullWhenItemNotFound() throws SQLException {
        executeQuery(util, "select * from ACCOUNTS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedAccount1.getName()).thenReturn(expectedAccount2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedAccount1.getId()).thenReturn(expectedAccount2.getId());
        when(resultSet.getFloat("money")).thenReturn(expectedAccount1.getMoney()).thenReturn(expectedAccount2.getMoney());
        when(resultSet.getString("iban")).thenReturn(expectedAccount1.getIban()).thenReturn(expectedAccount2.getIban());


        account = repository.find(3);
        assertNull(account);
    }

    @Test
    public void testFindReturnsProperItem() throws SQLException {
        executeQuery(util, "select * from ACCOUNTS");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedAccount2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedAccount2.getId());
        when(resultSet.getFloat("money")).thenReturn(expectedAccount2.getMoney());
        when(resultSet.getString("iban")).thenReturn(expectedAccount2.getIban());

        account = repository.find(2);
        assertEquals(account.getName(), expectedAccount2.getName());
    }

    @Test
    public void testFindByNameThrowsExceptionWhenNull() throws SQLException {
        executeQuery(util, "select * from ACCOUNTS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedAccount1.getName()).thenReturn(expectedAccount2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedAccount1.getId()).thenReturn(expectedAccount2.getId());
        when(resultSet.getFloat("money")).thenReturn(expectedAccount1.getMoney()).thenReturn(expectedAccount2.getMoney());
        when(resultSet.getString("iban")).thenReturn(expectedAccount1.getIban()).thenReturn(expectedAccount2.getIban());


        List<Account> accounts = repository.findByName(null, "name");
        assertTrue(accounts.isEmpty());
    }

    @Test
    public void testFindByNameReturnsNullWhenItemsNotFound() throws SQLException {
        executeQuery(util, "select * from ACCOUNTS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedAccount1.getName()).thenReturn(expectedAccount2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedAccount1.getId()).thenReturn(expectedAccount2.getId());
        when(resultSet.getFloat("money")).thenReturn(expectedAccount1.getMoney()).thenReturn(expectedAccount2.getMoney());
        when(resultSet.getString("iban")).thenReturn(expectedAccount1.getIban()).thenReturn(expectedAccount2.getIban());

        List<Account> accounts = repository.findByName("", "nothing");

        assertTrue(accounts.isEmpty());
    }

    @Test
    public void testFindByNameReturnsProperItems() throws SQLException {
        executeQuery(util, "select * from ACCOUNTS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedAccount1.getName()).thenReturn(expectedAccount2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedAccount1.getId()).thenReturn(expectedAccount2.getId());
        when(resultSet.getFloat("money")).thenReturn(expectedAccount1.getMoney()).thenReturn(expectedAccount2.getMoney());
        when(resultSet.getString("iban")).thenReturn(expectedAccount1.getIban()).thenReturn(expectedAccount2.getIban());


        List<Account> accounts = repository.findByName("", expectedAccount2.getName());

        assertFalse(accounts.isEmpty());
        assertEquals(expectedAccount2.getName(), accounts.get(0).getName());
    }
}
