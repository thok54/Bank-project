package ejercicio.banco.service;

import java.util.Arrays;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.repository.CsvBankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceImplTest {

    private static final String FILE_NAME = "file-name-test";
    private static final String BANK_NAME = "name";
    private static final int BANK_ID = 10876;
    private Bank expectedBank = new Bank(10876);

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
        bankService.findBanks(FILE_NAME, BANK_NAME);

        verify(repository).findByName(FILE_NAME, BANK_NAME);
    }

    @Test
    public void testStoreBankCallsFindFromRepository() {
        bankService.storeBank(expectedBank);

        verify(repository).store(expectedBank);
    }

    @Test
    public void testUpdateBankCallsFindFromRepository() {
        bankService.updateBank(BANK_ID, expectedBank);

        verify(repository).update(BANK_ID, expectedBank);
    }

    @Test
    public void testDeleteBankCallsFindFromRepository() {
        bankService.deleteBank(BANK_ID);

        verify(repository).delete(BANK_ID);
    }
}