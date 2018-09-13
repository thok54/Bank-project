package test.java.ejercicio.banco.tester;

import junit.framework.TestCase;
import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.repository.CsvAccountRepository;
import main.java.ejercicio.banco.repository.CsvBankRepository;
import main.java.ejercicio.banco.repository.CsvPaymentRepository;
import main.java.ejercicio.banco.service.AccountService;
import main.java.ejercicio.banco.service.AccountServiceImpl;
import main.java.ejercicio.banco.service.BankService;
import main.java.ejercicio.banco.service.BankServiceImpl;
import main.java.ejercicio.banco.service.PaymentService;
import main.java.ejercicio.banco.service.PaymentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CsvPaymentRepositoryTest {

    private PaymentService paymentService;

    @Before
    public void setup() {
        this.paymentService = new PaymentServiceImpl(new CsvPaymentRepository());
    }


    @Test
    public void findAllTest(){

        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv");
        Bank testBank = bankServiceTest.processBank(bankFileTest);

        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.processAccounts(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        testBank.setUsers(accounts);

        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTest.csv");
        List<Payment> payments = paymentService.processPayments(paymentFileTest, testBank);
        Assert.assertTrue("Not getting the right Amount", payments.get(1).getAmount() == 87.25);
        //assertEquals(payments.get(1).getAmount(), 87.25);
    }

    @Test
    public void findTest(){
        Payment payment = ((PaymentServiceImpl) paymentService).findPayment(20);
        assertNull(payment);

    }


    @Test
    public void otherTests(){
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv");
        Bank testBank = bankServiceTest.processBank(bankFileTest);

        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.processAccounts(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        testBank.setUsers(accounts);

        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTest.csv");
        List<Payment> payments = paymentService.processPayments(paymentFileTest, testBank);


        Payment payment = payments.get(0);
        float money = payment.getAmount();

        ((PaymentServiceImpl) paymentService).storePayment(payment);
        ((PaymentServiceImpl) paymentService).updatePayment(0, payment);
        ((PaymentServiceImpl) paymentService).deletePayment(0);

        assertTrue("Payment should remain same", payment.getAmount()==money);
        //assertEquals("These commands should not modify accounts", account.getMoney(), money);

    }

}
