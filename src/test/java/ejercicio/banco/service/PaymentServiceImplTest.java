package ejercicio.banco.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import ejercicio.banco.dto.Account;
import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.Payment;
import ejercicio.banco.repository.CsvAccountRepository;
import ejercicio.banco.repository.CsvBankRepository;
import ejercicio.banco.repository.CsvPaymentRepository;
import ejercicio.banco.service.AccountService;
import ejercicio.banco.service.AccountServiceImpl;
import ejercicio.banco.service.BankService;
import ejercicio.banco.service.BankServiceImpl;
import ejercicio.banco.service.PaymentService;
import ejercicio.banco.service.PaymentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

    private static final String FILE_NAME = "file-name-test";
    private static final int BANK_ID = 10876;
    private static final int PAYMENT_ID = 27428;
    Bank tsBank = new Bank(BANK_ID);
    Payment expectedPayment = new Payment(PAYMENT_ID);

    @Mock
    private CsvPaymentRepository repository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    public void testProcessPaymentCallsFindAllFromRepository() {
       List<Payment> payments = paymentService.processPayments(FILE_NAME,tsBank);

        verify(repository).findAll(FILE_NAME);
    }

    @Test
    public void testProcessPaymentsReturnsCorrectList() {
        // Given
        when(repository.findAll(FILE_NAME)).thenReturn(Arrays.asList(expectedPayment));

        // When
        List<Payment> payments = paymentService.processPayments(FILE_NAME,tsBank);

        // Then
        verify(repository).findAll(FILE_NAME);

        assertNotNull(payments);
        assertEquals(PAYMENT_ID, payments.get(0).getPaymentId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessPaymentsWithNullFilenameThrowsIllegalArgumentException() {
        paymentService.processPayments(null, null);
    }

    @Test
    public void testFindPaymentCallsFindFromRepository() {
        paymentService.findPayment(PAYMENT_ID);

        verify(repository).find(PAYMENT_ID);
    }

    @Test
    public void testFindPaymentsCallsFindFromRepository() {
        paymentService.findPayments(FILE_NAME, "27428");

        verify(repository).findById(FILE_NAME, PAYMENT_ID);
    }

    @Test
    public void testStorePaymentsCallsFindFromRepository() {
        paymentService.storePayment(expectedPayment);

        verify(repository).store(expectedPayment);
    }

    @Test
    public void testUpdatePaymentCallsFindFromRepository() {
        paymentService.updatePayment(PAYMENT_ID,expectedPayment);

        verify(repository).update(PAYMENT_ID,expectedPayment);
    }

    @Test
    public void testUpdateDeleteCallsFindFromRepository() {
        paymentService.deletePayment(PAYMENT_ID);

        verify(repository).delete(PAYMENT_ID);
    }
}