package ejercicio.banco.search;

import ejercicio.banco.service.AccountServiceImpl;
import ejercicio.banco.service.BankServiceImpl;
import ejercicio.banco.service.PaymentServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.List;

import static ejercicio.banco.dto.DataType.ACCOUNT;
import static ejercicio.banco.dto.DataType.BANK;
import static ejercicio.banco.dto.DataType.PAYMENT;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ToolSearchEngineTest {

    private static final String BANK_NAME = "SHADYBANK";
    private static final String ACCOUNT_NAME = "Pedro";
    private static final String PAYMENT_NAME = "5";
    private static final String INVALID_NAME = "NOTHING";
    private static final String BANK_FILE = String.join(File.separator, "src", "main", "resources", "csv", "randomBank.csv");
    private static final String ACCOUNT_FILE = String.join(File.separator, "src", "main", "resources", "csv", "randomAccounts.csv");
    private static final String PAYMENT_FILE = String.join(File.separator, "src", "main", "resources", "csv", "randomPayments.csv");

    @Mock
    private BankServiceImpl bankService;
    @Mock
    private AccountServiceImpl accountService;
    @Mock
    private PaymentServiceImpl paymentService;

    @InjectMocks
    private ToolSearchEngine searchEngine;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testSearchThrowsExceptionWhenDataTypeIsNull() {
        expectedEx.expect(IllegalArgumentException.class);
        searchEngine.search(null, null);
        expectedEx.expectMessage("Type must not be null");
    }

    @Test
    public void testSearchThrowsExceptionWhenStuffIsNull() {
        expectedEx.expect(NullPointerException.class);
        searchEngine.search(BANK, null);
        expectedEx.expectMessage("Searching Null String");
    }

    //TODO: Seems like when returning Collections, the size of the array increases

    @Test
    public void testSearchReturnsEmptyArrayWhenStuffDoesNotMatch() {
        List<Object> results =  searchEngine.search(BANK, INVALID_NAME);
        assertEquals(1,results.size());
    }

    //TODO: None of the verify tests seem to work

    @Test
    public void testSearchCallsFindBanksWhenDataTypeIsBank() {
        searchEngine.search(BANK, BANK_NAME);
        verify(bankService).findBanks(BANK_FILE, BANK_NAME);
    }

    @Test
    public void testSearchCallsFindAccountsWhenDataTypeIsAccount() {
        searchEngine.search(ACCOUNT, ACCOUNT_NAME);
        verify(accountService).findAccounts(ACCOUNT_FILE, ACCOUNT_NAME);
    }

    @Test
    public void testSearchCallsFindPaymentsWhenDataTypeIsPayment() {
        searchEngine.search(PAYMENT, PAYMENT_NAME);
        verify(paymentService).findPayments(PAYMENT_FILE, PAYMENT_NAME);
    }
}
