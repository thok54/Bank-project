package ejercicio.banco.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.repository.CsvBankRepository;
import ejercicio.banco.service.BankService;
import ejercicio.banco.service.BankServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BankServiceImplTest {

    private static final String FILE_NAME = "file-name-test";
    private static final int BANK_ID = 10876;
    Bank expectedBank = new Bank(10876);

    @Mock
    private CsvBankRepository repository;

    @InjectMocks
    private BankServiceImpl bankService;



    @Test
    public void testProcessBankCallsFindAllFromRepository() {
        Bank bank = bankService.processBank(FILE_NAME);

        verify(repository).findAll(FILE_NAME);
    }

    @Test
    public void testProcessBankReturnsCorrectList() {
        // Given
        when(repository.findAll(FILE_NAME)).thenReturn(Arrays.asList(expectedBank));

        // When
        Bank bank = bankService.processBank(FILE_NAME);

        // Then
        verify(repository).findAll(FILE_NAME);

        assertNotNull(bank);
        assertEquals(BANK_ID, bank.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessBankWithNullFilenameThrowsIllegalArgumentException() {
        bankService.processBank(null);
    }


    @Test
    public void testFindBankCallsFindFromRepository() {
        bankService.findBank(BANK_ID);

        verify(repository).find(BANK_ID);
    }

    @Test
    public void testFindBanksCallsFindFromRepository() {
        bankService.findBanks(FILE_NAME, "NAME");

        verify(repository).findByName(FILE_NAME, "NAME");
    }

    @Test
    public void testStoreBankCallsFindFromRepository() {
        bankService.storeBank(expectedBank);

        verify(repository).store(expectedBank);
    }

    @Test
    public void testUpdateBankCallsFindFromRepository() {
        bankService.updateBank(BANK_ID,expectedBank);

        verify(repository).update(BANK_ID,expectedBank);
    }

    @Test
    public void testUpdateDeleteBankCallsFindFromRepository() {
        bankService.deleteBank(BANK_ID);

        verify(repository).delete(BANK_ID);
    }
}