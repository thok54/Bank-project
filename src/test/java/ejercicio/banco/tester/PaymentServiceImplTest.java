package test.java.ejercicio.banco.tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.repository.CsvAccountRepository;
import main.java.ejercicio.banco.repository.CsvBankRepository;
import main.java.ejercicio.banco.repository.CsvPaymentRepository;
import main.java.ejercicio.banco.service.*;
import org.junit.Test;

import static org.junit.Assert.*;


//Tests if PaymentServiceImpl is generating Payment properly from files
public class PaymentServiceImplTest {

    @Test
    public void manage() throws FileNotFoundException {
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv");
        Bank testBank = bankServiceTest.manage(bankFileTest);

        AccountService accountServiceTest = new AccountServiceImpl(new CsvAccountRepository());
        List<Account> accounts = accountServiceTest.manage(String.join(File.separator, "src", "test", "resources", "csv", "accountsTest.csv"));
        testBank.setUsers(accounts);

        PaymentService paymentServiceTest = new PaymentServiceImpl(new CsvPaymentRepository());
        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTest.csv");
        List<Payment> payments = paymentServiceTest.manage(paymentFileTest, testBank);
        assertTrue("Not getting the right Amount", payments.get(1).getAmount() == 87.25);
    }
}