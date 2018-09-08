package test.java.ejercicio.banco.tester;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.repository.CsvBankRepository;
import main.java.ejercicio.banco.service.BankService;
import main.java.ejercicio.banco.service.BankServiceImpl;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class CsvBankRepositoryTest {

    @Test
    public void findAllTest(){
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        Bank bank = bankServiceTest.processBank(String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv"));
        assertTrue("Wrong banks being processed", bank.getId() == 3789);
    }

    @Test
    public void findTest(){
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        Bank bank = ((BankServiceImpl) bankServiceTest).findBank(20);
        assertTrue("Using find on index out of bonds should return null", bank == null);

    }

}
