package ejercicio.banco.web;

import ejercicio.banco.dto.Payment;

import ejercicio.banco.repository.jpa.PaymentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentControllerTest {
    //TODO: Payment controller test using memory
    private Payment payment;
    private Payment expectedPayment1 = new Payment(3, 5, (float) 1.87);
    private Payment expectedPayment2 = new Payment(4, 6, (float) 3.41);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaymentRepository mySqlPaymentRepository;


    @Test
    public void whenFind_thenReturnPayment() throws SQLException {
        entityManager.persist(expectedPayment1);
        entityManager.flush();

        payment = mySqlPaymentRepository.getOne(1);

        assertThat(payment).isEqualTo(expectedPayment1);
    }
}
