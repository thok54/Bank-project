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
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CsvPaymentRepositoryTest {

    @Test
    public void findAllTest(){

        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv");
        Bank testBank = bankServiceTest.processBank(bankFileTest);

        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.processAccounts(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        testBank.setUsers(accounts);

        PaymentService paymentServiceTest = new PaymentServiceImpl(new CsvPaymentRepository());
        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTest.csv");
        List<Payment> payments = paymentServiceTest.processPayments(paymentFileTest, testBank);
        TestCase.assertTrue("Wrong payments being processed", payments.get(1).getAmount() == 87.25);
    }

    @Test
    public void findTest(){
        PaymentService paymentServiceTest = new PaymentServiceImpl(new CsvPaymentRepository());
        Payment payment = ((PaymentServiceImpl) paymentServiceTest).findPayment(20);
        assertTrue("Using find on index out of bonds should return null", payment == null);

    }

}
