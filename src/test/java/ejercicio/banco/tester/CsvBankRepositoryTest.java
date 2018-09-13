package test.java.ejercicio.banco.tester;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.repository.CsvBankRepository;
import main.java.ejercicio.banco.service.BankService;
import main.java.ejercicio.banco.service.BankServiceImpl;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CsvBankRepositoryTest {

    @Test
    public void findAllTest(){
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        int id = -1;
        try {
            Bank bank = bankServiceTest.processBank(String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv"));
            id = bank.getId();
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("No bank was processed");
        }
            assertEquals("Wrong banks being processed", 3789, id);
    }

    @Test
    public void findTest(){
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        Bank bank = ((BankServiceImpl) bankServiceTest).findBank(20);
        assertNull(bank);

    }

    @Test
    public void storeBankTest(){
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        Bank bank = bankServiceTest.processBank(String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv"));
        int id = bank.getId();


        ((BankServiceImpl) bankServiceTest).storeBank(bank);

        assertTrue("Bank should remain same", bank.getId()==id);
        //assertEquals("These commands should not modify bank", bank.getId(), id);

    }


    @Test
    public void updateBankTest(){
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        Bank bank = bankServiceTest.processBank(String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv"));
        int id = bank.getId();


        ((BankServiceImpl) bankServiceTest).updateBank(0, bank);

        assertTrue("Bank should remain same", bank.getId()==id);
        //assertEquals("These commands should not modify bank", bank.getId(), id);

    }

    @Test
    public void DeleteBankTest(){
        BankService bankServiceTest = new BankServiceImpl(new CsvBankRepository());
        Bank bank = bankServiceTest.processBank(String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv"));
        int id = bank.getId();

        ((BankServiceImpl) bankServiceTest).deleteBank(0);

        assertTrue("Bank should remain same", bank.getId()==id);
        //assertEquals("These commands should not modify bank", bank.getId(), id);

    }

}
