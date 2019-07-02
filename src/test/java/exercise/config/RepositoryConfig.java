package exercise.config;

import exercise.repository.AccountRepository;
import exercise.repository.BankRepository;
import exercise.repository.PaymentRepository;
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
