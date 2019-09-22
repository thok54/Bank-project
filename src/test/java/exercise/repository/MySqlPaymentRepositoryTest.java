package exercise.repository;

import exercise.dto.Payment;
import exercise.util.DataBaseUtil;
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
public class MySqlPaymentRepositoryTest extends AbstractMySqlRepositoryTest {
    private Payment payment;
    private Payment expectedPayment1 = new Payment(1, 3, 5, (float) 1.87);
    private Payment expectedPayment2 = new Payment(2, 4, 6, (float) 3.41);

    @Mock
    private DataBaseUtil util;

    @InjectMocks
    MySqlPaymentRepository repository;

    @Test
    public void testFindAllReturnsFullList() throws SQLException {
        executeQuery(util, "select * from PAYMENTS");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(expectedPayment1.getId()).thenReturn(expectedPayment2.getId());
        when(resultSet.getInt("bankId")).thenReturn(expectedPayment1.getBankId()).thenReturn(expectedPayment2.getBankId());
        when(resultSet.getInt("userId")).thenReturn(expectedPayment1.getUserId()).thenReturn(expectedPayment2.getUserId());
        when(resultSet.getFloat("amount")).thenReturn(expectedPayment1.getAmount()).thenReturn(expectedPayment2.getAmount());

        List<Payment> expectedPayments = new ArrayList();
        expectedPayments.add(expectedPayment1);
        expectedPayments.add(expectedPayment2);

        List<Payment> full = repository.findAll();
        assertFalse(full.isEmpty());
        assertEquals(expectedPayments, full);
    }


    @Test
    public void testFindReturnsNullWhenItemNotFound() throws SQLException {
        executeQuery(util, "select * from PAYMENTS where id = 3");

        when(resultSet.next()).thenReturn(false);
        payment = repository.find(3);
        assertNull(payment);
    }

    @Test
    public void testFindReturnsProperItem() throws SQLException {
        executeQuery(util, "select * from PAYMENTS where id = 2");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("bankId")).thenReturn(expectedPayment2.getBankId());
        when(resultSet.getInt("userId")).thenReturn(expectedPayment2.getUserId());
        when(resultSet.getFloat("amount")).thenReturn(expectedPayment2.getAmount());

        payment = repository.find(2);
        assertEquals(payment.getBankId(), expectedPayment2.getBankId());
    }

    @Test
    public void testFindByBankIDReturnsNullWhenItemsNotFound() throws SQLException {
        executeQuery(util, "select * from PAYMENTS where bankId = 14");

        when(resultSet.next()).thenReturn(false);

        List<Payment> payments = repository.findAllByBankId(14);
        assertTrue(payments.isEmpty());
    }

    @Test
    public void testFindByBankIDReturnsProperItems() throws SQLException {
        executeQuery(util, "select * from PAYMENTS where bankId = " + expectedPayment2.getBankId());

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(expectedPayment1.getId()).thenReturn(expectedPayment2.getId());
        when(resultSet.getInt("userId")).thenReturn(expectedPayment1.getUserId()).thenReturn(expectedPayment2.getUserId());
        when(resultSet.getFloat("amount")).thenReturn(expectedPayment1.getAmount()).thenReturn(expectedPayment2.getAmount());


        List<Payment> payments = repository.findAllByBankId(expectedPayment2.getBankId());
        assertFalse(payments.isEmpty());
        assertEquals(expectedPayment2.getBankId(), payments.get(0).getBankId());
    }
}
