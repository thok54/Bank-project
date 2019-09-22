package exercise.service;

import exercise.dto.Account;
import exercise.dto.Bank;
import exercise.dto.InternalDto;
import exercise.dto.Payment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static exercise.dto.DataType.ACCOUNT;
import static exercise.dto.DataType.BANK;
import static exercise.dto.DataType.PAYMENT;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class DataGeneratorFactoryTest {

    private static final String BANK_FILE = String.join(File.separator, "src", "test", "resources", "csv", "bank-file-test");
    private static final String ACCOUNT_FILE = String.join(File.separator, "src", "test", "resources", "csv", "account-file-test");
    private static final String PAYMENT_FILE = String.join(File.separator, "src", "test", "resources", "csv", "payment-file-test");
    private DataGeneratorFactory factory = new DataGeneratorFactory();
    private Bank expectedBank = new Bank(108);
    private Account expectedAccount = new Account(10876);
    private Payment expectedPayment = new Payment(24876);

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testGenerateReturnsItem() {
        InternalDto bank = factory.generate(BANK, BANK_FILE);
        assertNotNull(bank);
    }

    @Test
    public void testGenerateThrowsExceptionWhenNullFile() {
        expectedEx.expect(IllegalArgumentException.class);
        factory.generate(BANK, null);
        expectedEx.expectMessage("Filename must not be null");
    }

    @Test
    public void testGenerateThrowsExceptionWhenNullDataType() {
        expectedEx.expect(IllegalArgumentException.class);
        factory.generate(null, BANK_FILE);
        expectedEx.expectMessage("Datatype must not be null");
    }

    @Test
    public void testGetDataTypeThrowsExceptionWhenNullFile() {
        expectedEx.expect(IllegalArgumentException.class);
        factory.getDataType(null, null);
        expectedEx.expectMessage("Filename must not be null");
    }

    @Test
    public void testGetDataTypeThrowsExceptionWhenNullDataType() {
        expectedEx.expect(IllegalArgumentException.class);
        factory.getDataType(null, BANK_FILE);
        expectedEx.expectMessage("Datatype must not be null");
    }

    @Test
    public void testGetBankReturnsBank() {
        InternalDto bank = factory.getDataType(BANK, BANK_FILE);
        assertNotNull(bank);
    }

    @Test
    public void testGetAccountReturnsAccount() {
        InternalDto account = factory.getDataType(ACCOUNT, ACCOUNT_FILE);
        assertNotNull(account);
    }

    @Test
    public void testGetPaymentReturnsPayment() {
        InternalDto payment = factory.getDataType(PAYMENT, PAYMENT_FILE);
        assertNotNull(payment);
    }
}
