package ejercicio.banco.service;

import java.util.Arrays;
import java.util.List;

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
    private Payment expectedPayment = new Payment(PAYMENT_ID);

    @Mock
    private MySqlPaymentRepository repository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    public void testProcessPaymentCallsFindAllFromRepository() {
        List<Payment> payments = paymentService.process();
        verify(repository).findAll();
    }

    @Test
    public void testProcessPaymentsReturnsCorrectList() {
        when(repository.findAll()).thenReturn(Arrays.asList(expectedPayment));
        List<Payment> payments = paymentService.process();
        verify(repository).findAll();

        assertNotNull(payments);
        assertEquals(PAYMENT_ID, payments.get(0).getId());
    }

    @Test
    public void testFindPaymentCallsFindFromRepository() {
        paymentService.find(PAYMENT_ID);
        verify(repository).find(PAYMENT_ID);
    }

    @Test
    public void testFindPaymentsCallsFindByNameFromRepository() {
        paymentService.findByBankId(PAYMENT_ID);
        verify(repository).findByBankId(PAYMENT_ID);
    }

    @Test
    public void testStorePaymentsCallsStoreFromRepository() {
        paymentService.store(expectedPayment);
        verify(repository).store(expectedPayment);
    }

    @Test
    public void testUpdatePaymentCallsUpdateFromRepository() {
        paymentService.update(PAYMENT_ID, expectedPayment);
        verify(repository).update(PAYMENT_ID, expectedPayment);
    }

    @Test
    public void testDeleteCallsDeleteFromRepository() {
        paymentService.delete(PAYMENT_ID);
        verify(repository).delete(PAYMENT_ID);
    }
}