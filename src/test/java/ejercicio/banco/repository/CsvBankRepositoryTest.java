package test.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.repository.CsvBankRepository;
import main.java.ejercicio.banco.service.BankService;
import main.java.ejercicio.banco.service.BankServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CsvBankRepositoryTest {

    private CsvBankRepository repository;

    @Before
    public void setup() {
        this.repository = new CsvBankRepository();
    }

    @Test
    public void findAllTest(){
        //Given
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv");
        //When
        List<Bank> banks = repository.findAll(bankFileTest);
        int id = banks.get(0).getId();
        //Then
        assertEquals("Wrong banks being processed", 3789, id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findAllThrowsExceptionWhenFileNotFoundTest(){
        //Given
        String bankFileTest = String.join(File.separator, "src", "test", "resources", "csv", "mentTd2fd2est.csv");

        //When
        repository.findAll(bankFileTest);
    }

    @Test
    public void findTest(){
        //When
        Bank bank = repository.find(20);
        //Then
        assertNull(bank);
    }

    @Test
    public void storeBankTest(){
        //Given
        Bank bank = repository.findAll(String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv")).get(0);
        int id = bank.getId();
        //When
        repository.store(bank);
        //Then
        assertTrue("Bank should remain same", bank.getId()==id);
    }


    @Test
    public void updateBankTest(){
        //Given
        Bank bank = repository.findAll(String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv")).get(0);
        int id = bank.getId();
        //When
        repository.update(0,bank);
        //Then
        assertTrue("Bank should remain same", bank.getId()==id);
    }

    @Test
    public void DeleteBankTest(){
        //Given
        Bank bank = repository.findAll(String.join(File.separator, "src", "test", "resources", "csv", "bankTest.csv")).get(0);
        int id = bank.getId();
        //When
        repository.delete(0);
        //Then
        assertTrue("Bank should remain same", bank.getId()==id);
    }

}
