package test.java;

import java.io.FileNotFoundException;
import java.util.List;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.service.BankManager;
import main.java.ejercicio.banco.service.BankManagerImpl;
import main.java.ejercicio.banco.service.PaymentManager;
import main.java.ejercicio.banco.service.PaymentManagerImpl;

import static org.junit.Assert.*;


//Tests if PaymentManagerImpl is generating Payment properly from files
public class PaymentManagerImplTest {

    @org.junit.Test
    public void manage() throws FileNotFoundException {
        BankManager bankManagerTest = new BankManagerImpl();
        String bankFileTest = "src\\test\\resources\\bankTest.csv";
        Bank testBank = bankManagerTest.manage(bankFileTest);

        PaymentManager paymentManagerTest = new PaymentManagerImpl();
        String paymentFileTest = "src\\test\\resources\\paymentTest.csv";
        List<Payment> payments = paymentManagerTest.manage(paymentFileTest, testBank);
        assertTrue("Not getting the right Amount", payments.get(1).getAmount() == 87.25);
    }
}