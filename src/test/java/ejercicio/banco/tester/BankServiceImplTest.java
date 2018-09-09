package test.java.ejercicio.banco.tester;

import java.io.File;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.repository.CsvBankRepository;
import main.java.ejercicio.banco.service.BankService;
import main.java.ejercicio.banco.service.BankServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


//Tests if BankServiceImpl is generating Banks properly from files
public class BankServiceImplTest {

    @Test
    public void manage() {
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv");
        Bank testBank = bankServiceTest.processBank(bankFileTest);
        assertEquals("TestAddress", testBank.getAddress());
    }
}