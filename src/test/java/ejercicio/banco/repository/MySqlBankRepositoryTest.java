package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;
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
public class MySqlBankRepositoryTest {
    private static final String TABLE = "bank_project_test";
    private static final String NOTHING = "nothing";
    private static final String EMPTYTABLE = "empty";
    private List<Bank> banks = new ArrayList();
    private List<Bank> empty = new ArrayList();
    private Bank bank;
    private Bank expectedBank1 = new Bank(1, "bestBank", "right here");
    private Bank expectedBank2 = new Bank(2, "Worst ATM", "over there");
    private List<Bank> full = new ArrayList();

    @Mock
    private DataBaseUtil util;

    @InjectMocks
    MySqlBankRepository repository;

    @Test
    public void testFindAllReturnsEmptyListWhenNull() {
        banks = repository.findAll(null);
        assertEquals(empty, banks);
    }

    @Test
    public void testFindAllReturnsEmptyListWhenListNotFound() {
        banks = repository.findAll(NOTHING);
        assertEquals(empty, banks);
    }

    @Test
    public void testFindReturnsEmptyListWhenListIsEmpty() {
        banks = repository.findAll(EMPTYTABLE);
        assertEquals(empty, banks);
    }

    @Test
    public void testFindAllReturnsFullList() {
        full.add(expectedBank1);
        full.add(expectedBank2);
        banks = repository.findAll(TABLE);
        assertEquals(full.size(), banks.size());
    }


    @Test
    public void testFindReturnsNullWhenItemNotFound() {
        bank = repository.find(3);
        assertEquals(bank, null);
    }

    @Test
    public void testFindReturnsProperItem() {
        bank = repository.find(2);
        assertEquals(bank.getName(), expectedBank2.getName());
    }

    @Test
    public void testFindByNameThrowsExceptionWhenNull() {
        banks = repository.findByName(null, "name");
        assertEquals(empty, banks);
    }

    @Test
    public void testFindByNameThrowsExceptionWhenListIsEmpty() {
        banks = repository.findByName(EMPTYTABLE, "name");
        assertEquals(empty, banks);
    }

    @Test
    public void testFindByNameReturnsNullWhenItemsNotFound() {
        banks = repository.findByName(TABLE, "nothing");
        assertEquals(empty, banks);
    }

    @Test
    public void testFindByNameReturnsProperItems() {
        banks = repository.findByName(TABLE, expectedBank2.getName());
        assertEquals(expectedBank2.getName(), banks.get(0).getName());
    }
}
