package test.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.service.*;
import org.junit.Test;

import static org.junit.Assert.*;


//Tests if PaymentManagerImpl is generating Payment properly from files
public class PaymentManagerImplTest {

    @Test
    public void manage() throws FileNotFoundException {
        BankManager bankManagerTest = new BankManagerImpl();
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "bankTest.csv");
        Bank testBank = bankManagerTest.manage(bankFileTest);

        AccountManager accountManagerTest = new AccountManagerImpl();
        List<Account> accounts = accountManagerTest.manage(String.join(File.separator, "src", "test", "resources", "accountsTest.csv"));
        testBank.setUsers(accounts);

        PaymentManager paymentManagerTest = new PaymentManagerImpl();
        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "paymentTest.csv");
        List<Payment> payments = paymentManagerTest.manage(paymentFileTest, testBank);
        assertTrue("Not getting the right Amount", payments.get(1).getAmount() == 87.25);
    }
}