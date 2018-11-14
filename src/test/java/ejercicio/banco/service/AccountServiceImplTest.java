package ejercicio.banco.service;

import ejercicio.banco.dto.Account;
import ejercicio.banco.repository.AccountNotFoundException;
import ejercicio.banco.repository.CsvAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    private static final String FILE_NAME = "file-name-test";
    private static final int ACCOUNT_ID = 10876;
    private static final int INVALID_ACCOUNT_ID = -123;
    Account expectedAccount = new Account(10876);

    @Mock
    private CsvAccountRepository repository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testProcessAccountsCallsFindAllFromRepository() {
        List<Account> accounts = accountService.processAccounts(FILE_NAME);

        verify(repository).findAll(FILE_NAME);
    }

    @Test
    public void testProcessAccountsReturnsCorrectList() {
        // Given
        when(repository.findAll(FILE_NAME)).thenReturn(Arrays.asList(expectedAccount));

        // When
        List<Account> accounts = accountService.processAccounts(FILE_NAME);

        // Then
        verify(repository).findAll(FILE_NAME);

        assertNotNull(accounts);
        assertEquals(ACCOUNT_ID, accounts.get(0).getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessAccountsWithNullFilenameThrowsIllegalArgumentException() {
        accountService.processAccounts(null);
    }

    @Test
    public void testFindAccountCallsFindFromRepository() {
        accountService.findAccount(ACCOUNT_ID);

        verify(repository).find(ACCOUNT_ID);
    }

    @Test
    public void testFindAccountReturnsNullIfAccountNotFoundExceptionIsThrown() {
        // TODO: Search how to assert message in thrown exceptions. Tip: Rules
        when(repository.find(INVALID_ACCOUNT_ID)).thenThrow(new AccountNotFoundException(""));

        Account account = accountService.findAccount(INVALID_ACCOUNT_ID);

        assertNull(account);
    }


    @Test
    public void testFindAccountsCallsFindFromRepository() {
        accountService.findAccounts(FILE_NAME, "NAME");

        verify(repository).findByName(FILE_NAME, "NAME");
    }

    @Test
    public void testStoreAccountCallsFindFromRepository() {
        accountService.storeAccount(expectedAccount);

        verify(repository).store(expectedAccount);
    }

    @Test
    public void testUpdateAccountCallsFindFromRepository() {
        accountService.updateAccount(ACCOUNT_ID,expectedAccount);

        verify(repository).update(ACCOUNT_ID,expectedAccount);
    }

    @Test
    public void testUpdateDeleteAccountCallsFindFromRepository() {
        accountService.deleteAccount(ACCOUNT_ID);

        verify(repository).delete(ACCOUNT_ID);
    }
}