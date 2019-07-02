package exercise.repository;

import exercise.dto.Account;
import exercise.util.DataBaseUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MySqlAccountRepositoryTest extends AbstractMySqlRepositoryTest {

    private Account account;
    private Account expectedAccount1 = new Account(1, "Peter", (float) 3.00, "PIPIRANA87");
    private Account expectedAccount2 = new Account(2, "Aurelio", (float) 8.49, "SATURN15STINKS");
    private Account expectedAccount3 = new Account(3, "Manolola", (float) 14.41, "N0105E");

    @Mock
    private DataBaseUtil util;

    @InjectMocks
    MySqlAccountRepository repository;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testFindAllReturnsFullList() throws SQLException {
        executeQuery(util, "select * from ACCOUNTS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedAccount1.getName()).thenReturn(expectedAccount2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedAccount1.getId()).thenReturn(expectedAccount2.getId());
        when(resultSet.getFloat("money")).thenReturn(expectedAccount1.getMoney()).thenReturn(expectedAccount2.getMoney());
        when(resultSet.getString("iban")).thenReturn(expectedAccount1.getIban()).thenReturn(expectedAccount2.getIban());

        List<Account> expectedAccounts = new ArrayList();
        expectedAccounts.add(expectedAccount1);
        expectedAccounts.add(expectedAccount2);

        List<Account> full = repository.findAll();
        assertFalse(full.isEmpty());
        assertEquals(expectedAccounts, full);
    }


    @Test
    public void testFindReturnsNullWhenItemNotFound() throws SQLException {
        executeQuery(util, "select * from ACCOUNTS where id = 3");

        when(resultSet.next()).thenReturn(false);

        account = repository.find(3);
        assertNull(account);
    }

    @Test
    public void testFindReturnsProperItem() throws SQLException {
        executeQuery(util, "select * from ACCOUNTS where id = 2");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedAccount2.getName());
        when(resultSet.getFloat("money")).thenReturn(expectedAccount2.getMoney());
        when(resultSet.getString("iban")).thenReturn(expectedAccount2.getIban());

        account = repository.find(2);
        assertEquals(expectedAccount2, account);
    }

    @Test
    public void testFindByNameReturnsEmptyListWhenItemsNotFound() throws SQLException {
        executeQuery(util, String.format("select * from ACCOUNTS where name = %s", "nothing"));

        when(resultSet.next()).thenReturn(false);

        List<Account> accounts = repository.findAllByName("nothing");
        assertTrue(accounts.isEmpty());
    }

    @Test
    public void testFindByNameReturnsProperItems() throws SQLException {
        executeQuery(util, String.format("select * from ACCOUNTS where name = %s", expectedAccount2.getName()));

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(expectedAccount1.getId()).thenReturn(expectedAccount2.getId());
        when(resultSet.getFloat("money")).thenReturn(expectedAccount1.getMoney()).thenReturn(expectedAccount2.getMoney());
        when(resultSet.getString("iban")).thenReturn(expectedAccount1.getIban()).thenReturn(expectedAccount2.getIban());


        List<Account> accounts = repository.findAllByName(expectedAccount2.getName());

        assertFalse(accounts.isEmpty());
        assertEquals(expectedAccount2.getName(), accounts.get(0).getName());
    }

    //TODO: Repository tests(store,update,delete,reset)

    @Test
    public void testStoreStoresProperAccount() throws SQLException {

    }

    @Test
    public void testUpdateUpdatesProperAccount() throws SQLException {

    }

    @Test
    public void testUpdateThrowsExceptionWhenIdNotFound() throws SQLException {

    }

    @Test
    public void testDeleteDeletesProperAccount() throws SQLException {

    }

    @Test
    public void testDeleteThrowsExceptionWhenIdNotFound() throws SQLException {

    }

    @Test
    public void testResetResetsProperAccount() throws SQLException {

    }

    @Test
    public void testResetThrowsExceptionWhenAccountFound() throws SQLException {

    }
}
