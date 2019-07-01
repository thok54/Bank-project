package ejercicio.banco.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ejercicio.banco.Application;
import ejercicio.banco.config.RepositoryConfig;
import ejercicio.banco.dto.Account;
import ejercicio.banco.repository.AccountRepository;
import ejercicio.banco.repository.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        Application.class,
        RepositoryConfig.class
})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccountControllerTest {

    private Account expectedAccount = new Account(1, "Peter", (float) 3.00, "PIPIRANA87");
    List<Account> expectedAccounts = Collections.singletonList(expectedAccount);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository mySqlAccountRepository;

    private String convertToJson(Object data) throws Exception {
        return objectMapper.writeValueAsString(data);
    }

    @Test
    public void shouldReturnAllAccounts() throws Exception {
        when(mySqlAccountRepository.findAll()).thenReturn(expectedAccounts);

        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(expectedAccounts)));
    }

    @Test
    public void shouldReturnAccountById() throws Exception {
        when(mySqlAccountRepository.find(1)).thenReturn(expectedAccount);

        mockMvc.perform(get("/account/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(expectedAccount)));
    }

    @Test
    public void shouldReturnNotFoundWhenAccountsIdDoesNotExist() throws Exception {
        when(mySqlAccountRepository.find(10)).thenThrow(new EntityNotFoundException(""));

        mockMvc.perform(get("/account/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnAccountByName() throws Exception {
        when(mySqlAccountRepository.findAllByName("Peter")).thenReturn(expectedAccounts);

        mockMvc.perform(get("/account/byName/Peter"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(expectedAccounts)));
    }

    @Test
    public void shouldReturnNotFoundWhenAccountsNameDoesNotExist() throws Exception {
        when(mySqlAccountRepository.findAllByName("10")).thenThrow(new EntityNotFoundException(""));

        mockMvc.perform(get("/account/byName/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateNewAccount() throws Exception {
        mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON).content(convertToJson(expectedAccount)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateNewAccount() throws Exception {
        mockMvc.perform(put("/account/10").contentType(MediaType.APPLICATION_JSON).content(convertToJson(expectedAccount)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAccount() throws Exception {
        mockMvc.perform(delete("/account/10").contentType(MediaType.APPLICATION_JSON).content(convertToJson(expectedAccount)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldResetAccount() throws Exception {
        mockMvc.perform(put("/account/reset").contentType(MediaType.APPLICATION_JSON).content(convertToJson(expectedAccount)))
                .andExpect(status().isNoContent());
    }
}
