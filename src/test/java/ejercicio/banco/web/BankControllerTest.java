package ejercicio.banco.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ejercicio.banco.Application;
import ejercicio.banco.config.RepositoryConfig;
import ejercicio.banco.dto.Bank;
import ejercicio.banco.repository.BankRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        Application.class,
        RepositoryConfig.class
})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BankControllerTest {
    private Bank expectedBank = new Bank(1, "bestBank", "right here");
    List<Bank> expectedBanks = Collections.singletonList(expectedBank);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankRepository mySqlBankRepository;

    private String convertToJson(Object data) throws Exception {
        return objectMapper.writeValueAsString(data);
    }

    @Test
    public void shouldReturnAllBanks() throws Exception {
        when(mySqlBankRepository.findAll()).thenReturn(expectedBanks);

        mockMvc.perform(get("/bank"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(expectedBanks)));
    }

    @Test
    public void shouldReturnBankById() throws Exception {
        when(mySqlBankRepository.find(1)).thenReturn(expectedBank);

        mockMvc.perform(get("/bank/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(expectedBank)));
    }

    @Test
    public void shouldReturnNotFoundWhenBanksIdDoesNotExist() throws Exception {
        when(mySqlBankRepository.find(10)).thenThrow(new EntityNotFoundException(""));

        mockMvc.perform(get("/bank/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBankByName() throws Exception {
        when(mySqlBankRepository.findAllByName("bestBank")).thenReturn(expectedBanks);

        mockMvc.perform(get("/bank/byName/bestBank"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(expectedBanks)));
    }

    @Test
    public void shouldReturnNotFoundWhenBanksNameDoesNotExist() throws Exception {
        when(mySqlBankRepository.findAllByName("10")).thenThrow(new EntityNotFoundException(""));

        mockMvc.perform(get("/bank/byName/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateNewBank() throws Exception {
        mockMvc.perform(post("/bank").contentType(MediaType.APPLICATION_JSON).content(convertToJson(expectedBank)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateNewBank() throws Exception {
        mockMvc.perform(put("/bank/10").contentType(MediaType.APPLICATION_JSON).content(convertToJson(expectedBank)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAccount() throws Exception {
        mockMvc.perform(delete("/bank/10").contentType(MediaType.APPLICATION_JSON).content(convertToJson(expectedBank)))
                .andExpect(status().isNoContent());
    }

}
