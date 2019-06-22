package ejercicio.banco.config;

import ejercicio.banco.repository.AccountRepository;
import ejercicio.banco.repository.BankRepository;
import ejercicio.banco.repository.PaymentRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @MockBean
    public AccountRepository mySqlAccountRepository;

    @MockBean
    public BankRepository mySqlBankRepository;

    @MockBean
    public PaymentRepository mySqlPaymentRepository;
}
