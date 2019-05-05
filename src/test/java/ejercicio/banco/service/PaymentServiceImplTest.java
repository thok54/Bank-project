package ejercicio.banco.service;

import java.util.Arrays;
import java.util.List;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.Payment;
import ejercicio.banco.repository.MySqlPaymentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

    private static final int BANK_ID = 10876;
    private static final int PAYMENT_ID = 27428;
    private static final String PAYMENT_STRING = "27428";
    private Bank tsBank = new Bank(BANK_ID);
    private Payment expectedPayment = new Payment(PAYMENT_ID);

    @Mock
    private MySqlPaymentRepository repository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    public void testProcessPaymentCallsFindAllFromRepository() {
        List<Payment> payments = paymentService.processPayments(tsBank);

        verify(repository).findAll();
    }

    @Test
    public void testProcessPaymentsReturnsCorrectList() {
        // Given
        when(repository.findAll()).thenReturn(Arrays.asList(expectedPayment));

        // When
        List<Payment> payments = paymentService.processPayments(tsBank);

        // Then
        verify(repository).findAll();

        assertNotNull(payments);
        assertEquals(PAYMENT_ID, payments.get(0).getPaymentId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessPaymentsWithNullFilenameThrowsIllegalArgumentException() {
        paymentService.processPayments(null);
    }

    @Test
    public void testFindPaymentCallsFindFromRepository() {
        paymentService.findPayment(PAYMENT_ID);

        verify(repository).find(PAYMENT_ID);
    }

    @Test
    public void testFindPaymentsCallsFindFromRepository() {
        paymentService.findPayments(PAYMENT_STRING);

        verify(repository).findByBankId(PAYMENT_ID);
    }

    @Test
    public void testStorePaymentsCallsFindFromRepository() {
        paymentService.storePayment(expectedPayment);

        verify(repository).store(expectedPayment);
    }

    @Test
    public void testUpdatePaymentCallsFindFromRepository() {
        paymentService.updatePayment(PAYMENT_ID, expectedPayment);

        verify(repository).update(PAYMENT_ID, expectedPayment);
    }

    @Test
    public void testDeleteCallsFindFromRepository() {
        paymentService.deletePayment(PAYMENT_ID);

        verify(repository).delete(PAYMENT_ID);
    }
}