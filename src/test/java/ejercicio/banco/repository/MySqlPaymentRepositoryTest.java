package ejercicio.banco.repository;

import ejercicio.banco.dto.Payment;
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
public class MySqlPaymentRepositoryTest {
    private static final String TABLE = "bank_project_test";
    private static final String NOTHING = "nothing";
    private static final String EMPTYTABLE = "empty";
    private List<Payment> payments = new ArrayList();
    private List<Payment> empty = new ArrayList();
    private Payment payment;
    private Payment expectedPayment1 = new Payment(1, 1, 2, (float)1.87);
    private Payment expectedPayment2 = new Payment(2, 2, 1, (float)3.41);
    private List<Payment> full = new ArrayList();

    @Mock
    private DataBaseUtil util;

    @InjectMocks
    MySqlPaymentRepository repository;

    @Test
    public void testFindAllReturnsEmptyListWhenNull() {
        payments = repository.findAll(null);
        assertEquals(empty, payments);
    }

    @Test
    public void testFindAllReturnsEmptyListWhenListNotFound() {
        payments = repository.findAll(NOTHING);
        assertEquals(empty, payments);
    }

    @Test
    public void testFindReturnsEmptyListWhenListIsEmpty() {
        payments = repository.findAll(EMPTYTABLE);
        assertEquals(empty, payments);
    }

    @Test
    public void testFindAllReturnsFullList() {
        full.add(expectedPayment1);
        full.add(expectedPayment2);
        payments = repository.findAll(TABLE);
        assertEquals(full.size(), payments.size());
    }


    @Test
    public void testFindReturnsNullWhenItemNotFound() {
        payment = repository.find(3);
        assertEquals(payment, null);
    }

    @Test
    public void testFindReturnsProperItem() {
        payment = repository.find(2);
        assertEquals(payment.getBankId(), expectedPayment2.getBankId());
    }

    @Test
    public void testFindByBankIDThrowsExceptionWhenNull() {
        payments = repository.findByBankId(null, 14);
        assertEquals(empty, payments);
    }

    @Test
    public void testFindByBankIDExceptionWhenListIsEmpty() {
        payments = repository.findByBankId(EMPTYTABLE, 14);
        assertEquals(empty, payments);
    }

    @Test
    public void testFindByBankIDReturnsNullWhenItemsNotFound() {
        payments = repository.findByBankId(TABLE, 14);
        assertEquals(empty, payments);
    }

    @Test
    public void testFindByBankIDReturnsProperItems() {
        payments = repository.findByBankId(TABLE, expectedPayment2.getBankId());
        assertEquals(expectedPayment2.getBankId(), payments.get(0).getBankId());
    }
}
