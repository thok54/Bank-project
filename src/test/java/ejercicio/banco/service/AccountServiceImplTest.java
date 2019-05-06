package ejercicio.banco.service;

import ejercicio.banco.dto.Account;
import ejercicio.banco.repository.AccountNotFoundException;
import ejercicio.banco.repository.MySqlAccountRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

    private static final String ACCOUNT_NAME = "name";
    private static final int ACCOUNT_ID = 10876;
    private static final int INVALID_ACCOUNT_ID = -123;
    private Account expectedAccount = new Account(10876);

    @Mock
    private MySqlAccountRepository repository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testProcessAccountsCallsFindAllFromRepository() {
        List<Account> accounts = accountService.process();
        verify(repository).findAll();
    }

    @Test
    public void testProcessAccountsReturnsCorrectList() {
        // Given
        when(repository.findAll()).thenReturn(Arrays.asList(expectedAccount));
        // When
        List<Account> accounts = accountService.process();
        // Then
        verify(repository).findAll();
        assertNotNull(accounts);
        assertEquals(ACCOUNT_ID, accounts.get(0).getId());
    }

    @Test
    public void testProcessAccountsWithNullFilenameThrowsIllegalArgumentException() {
        expectedEx.expect(IllegalArgumentException.class);
        accountService.process();
        expectedEx.expectMessage("Filename must not be null");
    }

    @Test
    public void testFindAccountCallsFindFromRepository() {
        accountService.find(ACCOUNT_ID);
        verify(repository).find(ACCOUNT_ID);
    }

    @Test
    public void testFindAccountReturnsNullIfAccountNotFoundExceptionIsThrown() {
        when(repository.find(INVALID_ACCOUNT_ID)).thenThrow(new AccountNotFoundException(""));
        Account account = accountService.find(INVALID_ACCOUNT_ID);
        assertNull(account);
    }


    @Test
    public void testFindAccountsCallsFindFromRepository() {
        accountService.findByName(ACCOUNT_NAME);
        verify(repository).findByName(ACCOUNT_NAME);
    }

    @Test
    public void testStoreAccountCallsFindFromRepository() {
        accountService.store(expectedAccount);
        verify(repository).store(expectedAccount);
    }

    @Test
    public void testUpdateAccountCallsFindFromRepository() {
        accountService.update(ACCOUNT_ID, expectedAccount);
        verify(repository).update(ACCOUNT_ID, expectedAccount);
    }

    @Test
    public void testDeleteAccountCallsFindFromRepository() {
        accountService.delete(ACCOUNT_ID);
        verify(repository).delete(ACCOUNT_ID);
    }
}