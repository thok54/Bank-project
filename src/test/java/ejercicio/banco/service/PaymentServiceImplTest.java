package test.java.ejercicio.banco.service;

import java.io.File;
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

import static junit.framework.TestCase.assertTrue;


//Tests if PaymentServiceImpl is generating Payment properly from files
public class PaymentServiceImplTest {

    @Test
    public void manage() {
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv");
        Bank testBank = bankServiceTest.processBank(bankFileTest);

        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.processAccounts(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        testBank.setUsers(accounts);

        PaymentService paymentServiceTest = new PaymentServiceImpl(new CsvPaymentRepository());
        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTest.csv");
        List<Payment> payments = paymentServiceTest.processPayments(paymentFileTest, testBank);
        assertTrue("Not getting the right Amount", payments.get(1).getAmount() == 87.25);
    }
}