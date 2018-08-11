package test.java;

import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.service.BankManager;
import main.java.ejercicio.banco.service.BankManagerImpl;


//Tests if BankManagerImpl is generating Banks properly from files
public class BankManagerImplTest {

    @org.junit.Test
    public void manage() throws FileNotFoundException {
        BankManager bankManagerTest = new BankManagerImpl();
        String bankFileTest = "src\\test\\resources\\bankTest.csv";
        Bank testBank = bankManagerTest.manage(bankFileTest);
        assertTrue("Not getting the right Address", testBank.getBankAddress().equals("TestAddress"));
    }
}