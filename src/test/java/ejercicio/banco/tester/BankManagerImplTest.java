package test.java.ejercicio.banco.tester;

import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.service.BankManager;
import main.java.ejercicio.banco.service.BankManagerImpl;
import org.junit.Test;


//Tests if BankManagerImpl is generating Banks properly from files
public class BankManagerImplTest {

    @Test
    public void manage() throws FileNotFoundException {
        BankManager bankManagerTest = new BankManagerImpl();
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv");
        Bank testBank = bankManagerTest.manage(bankFileTest);
        assertEquals("TestAddress", testBank.getBankAddress());
    }
}