package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;
import ejercicio.banco.util.DataBaseUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MySqlAccountRepositoryTest {
    private static final String TABLE = "bank_project_test";
    private static final String NOTHING = "nothing";
    private static final String EMPTYTABLE = "empty";
    private List<Account> accounts = new ArrayList();
    private List<Account> empty = new ArrayList();
    private Account account;
    private Account expectedAccount1 = new Account(1, "Peter",(float)3.00, "PIPIRANA87");
    private Account expectedAccount2 = new Account(2, "Aurelio", (float)8.49, "SATURN15STINKS");
    private List<Account> full = new ArrayList();

    @Mock
    private DataBaseUtil util;

    @InjectMocks
    MySqlAccountRepository repository;

    @Test
    public void testFindAllReturnsEmptyListWhenNull() {
        accounts = repository.findAll(null);
        assertEquals(empty, accounts);
    }

    @Test
    public void testFindAllReturnsEmptyListWhenListNotFound() {
        accounts = repository.findAll(NOTHING);
        assertEquals(empty, accounts);
    }

    @Test
    public void testFindReturnsEmptyListWhenListIsEmpty() {
        accounts = repository.findAll(EMPTYTABLE);
        assertEquals(empty, accounts);
    }

    @Test
    public void testFindAllReturnsFullList() {
        full.add(expectedAccount1);
        full.add(expectedAccount2);
        accounts = repository.findAll(TABLE);
        assertEquals(full.size(), accounts.size());
    }


    @Test
    public void testFindReturnsNullWhenItemNotFound() {
        account = repository.find(3);
        assertEquals(account, null);
    }

    @Test
    public void testFindReturnsProperItem() {
        account = repository.find(2);
        assertEquals(account.getName(), expectedAccount2.getName());
    }

    @Test
    public void testFindByNameThrowsExceptionWhenNull() {
        accounts = repository.findByName(null, "name");
        assertEquals(empty, accounts);
    }

    @Test
    public void testFindByNameThrowsExceptionWhenListIsEmpty() {
        accounts = repository.findByName(EMPTYTABLE, "name");
        assertEquals(empty, accounts);
    }

    @Test
    public void testFindByNameReturnsNullWhenItemsNotFound() {
        accounts = repository.findByName(TABLE, "nothing");
        assertEquals(empty, accounts);
    }

    @Test
    public void testFindByNameReturnsProperItems() {
        accounts = repository.findByName(TABLE, expectedAccount2.getName());
        assertEquals(expectedAccount2.getName(), accounts.get(0).getName());
    }
}
