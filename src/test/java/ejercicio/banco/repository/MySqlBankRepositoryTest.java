package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.util.DataBaseUtil;
import org.junit.Test;
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
public class MySqlBankRepositoryTest extends AbstractMySqlRepositoryTest {
    private Bank bank;
    private Bank expectedBank1 = new Bank(1, "bestBank", "right here");
    private Bank expectedBank2 = new Bank(2, "Worst ATM", "over there");

    @Mock
    private DataBaseUtil util;

    @InjectMocks
    MySqlBankRepository repository;


    @Test
    public void testFindAllReturnsFullList() throws SQLException {
        executeQuery(util, "select * from BANKS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedBank1.getName()).thenReturn(expectedBank2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedBank1.getId()).thenReturn(expectedBank2.getId());
        when(resultSet.getString("address")).thenReturn(expectedBank1.getAddress()).thenReturn(expectedBank2.getAddress());

        List<Bank> expectedBanks = new ArrayList();
        expectedBanks.add(expectedBank1);
        expectedBanks.add(expectedBank2);

        List<Bank> full = repository.findAll("");
        assertFalse(full.isEmpty());
        assertEquals(expectedBanks, full);
    }


    @Test
    public void testFindReturnsNullWhenItemNotFound() throws SQLException {
        executeQuery(util, "select * from BANKS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(expectedBank1.getId()).thenReturn(expectedBank2.getId());

        bank = repository.find(3);
        assertNull(bank);
    }

    @Test
    public void testFindReturnsProperItem() throws SQLException {
        executeQuery(util, "select * from BANKS");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedBank2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedBank2.getId());
        when(resultSet.getString("address")).thenReturn(expectedBank2.getAddress());

        bank = repository.find(2);
        assertEquals(bank.getName(), expectedBank2.getName());
    }

    @Test
    public void testFindByNameThrowsExceptionWhenNull() throws SQLException {
        executeQuery(util, "select * from BANKS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedBank1.getName()).thenReturn(expectedBank2.getName());

        List<Bank> banks = repository.findByName(null, "name");
        assertTrue(banks.isEmpty());
    }

    @Test
    public void testFindByNameReturnsNullWhenItemsNotFound() throws SQLException {
        executeQuery(util, "select * from BANKS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedBank1.getName()).thenReturn(expectedBank2.getName());


        List<Bank> banks = repository.findByName("", "nothing");
        assertTrue(banks.isEmpty());
    }

    @Test
    public void testFindByNameReturnsProperItems() throws SQLException {
        executeQuery(util, "select * from BANKS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn(expectedBank1.getName()).thenReturn(expectedBank2.getName());
        when(resultSet.getInt("id")).thenReturn(expectedBank1.getId()).thenReturn(expectedBank2.getId());
        when(resultSet.getString("address")).thenReturn(expectedBank1.getAddress()).thenReturn(expectedBank2.getAddress());


        List<Bank> banks = repository.findByName("", expectedBank2.getName());
        assertFalse(banks.isEmpty());
        assertEquals(expectedBank2.getName(), banks.get(0).getName());
    }
}
