package ejercicio.banco.service;

import java.util.Arrays;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.repository.MySqlBankRepository;
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

    private static final String BANK_NAME = "name";
    private static final int BANK_ID = 10876;
    private Bank expectedBank = new Bank(10876);

    @Mock
    private MySqlBankRepository repository;

    @InjectMocks
    private BankServiceImpl bankService;


    @Test
    public void testProcessBankCallsFindAllFromRepository() {
        bankService.process();
        verify(repository).findAll();
    }

    @Test
    public void testProcessBankReturnsCorrectList() {
        when(repository.findAll()).thenReturn(Arrays.asList(expectedBank));
        Bank bank = bankService.process().get(0);
        verify(repository).findAll();

        assertNotNull(bank);
        assertEquals(BANK_ID, bank.getId());
    }


    @Test
    public void testFindBankCallsFindFromRepository() {
        bankService.find(BANK_ID);
        verify(repository).find(BANK_ID);
    }

    @Test
    public void testFindBanksCallsFindByNameFromRepository() {
        bankService.findByName(BANK_NAME);
        verify(repository).findByName(BANK_NAME);
    }

    @Test
    public void testStoreBankCallsStoreFromRepository() {
        bankService.store(expectedBank);
        verify(repository).store(expectedBank);
    }

    @Test
    public void testUpdateBankCallsUpdateFromRepository() {
        bankService.update(BANK_ID, expectedBank);
        verify(repository).update(BANK_ID, expectedBank);
    }

    @Test
    public void testDeleteBankCallsDeleteFromRepository() {
        bankService.delete(BANK_ID);
        verify(repository).delete(BANK_ID);
    }
}