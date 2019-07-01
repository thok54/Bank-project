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
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentControllerTest {
    //TODO: Payment controller test using memory
    private Payment payment;
    private Payment expectedPayment1 = new Payment(3, 5, (float) 1.87);
    private Payment expectedPayment2 = new Payment(4, 6, (float) 3.41);
    List<Payment> expectedPayments = Arrays.asList(expectedPayment1, expectedPayment2);
    List<Payment> expectedPayments2 = Collections.singletonList(expectedPayment2);


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaymentRepository mySqlPaymentRepository;


    @Test
    public void findAllReturnsAllPayments() throws SQLException{
        entityManager.persist(expectedPayment1);
        entityManager.persist(expectedPayment2);
        entityManager.flush();

        List<Payment> payments = mySqlPaymentRepository.findAll();

        assertThat(payments).isEqualTo(expectedPayments);

    }

    @Test
    public void whenFind_thenReturnPayment() throws SQLException {
        entityManager.persist(expectedPayment1);
        entityManager.persist(expectedPayment2);
        entityManager.flush();

        payment = mySqlPaymentRepository.getOne(2);

        assertThat(payment).isEqualTo(expectedPayment2);
    }

    @Test//Does not return Null
    public void whenIdNotFound_FindReturnsNull() throws SQLException {
        entityManager.persist(expectedPayment1);
        entityManager.persist(expectedPayment2);
        entityManager.flush();

        payment = mySqlPaymentRepository.getOne(5);

        assertNull(payment);
    }

    @Test//Because I think is not working because bankId is NOT the primary key
    public void whenFindByBankId_thenReturnPayment() throws SQLException {
        entityManager.persist(expectedPayment1);
        entityManager.persist(expectedPayment2);
        entityManager.flush();

        List<Payment> payments = mySqlPaymentRepository.findAllByBankId(2);
        assertFalse(payments.isEmpty());
        assertEquals(expectedPayment2.getBankId(), payments.get(1).getBankId());
    }

    @Test
    public void whenIdNotFound_FindByBankIdReturnsEmptyList() throws SQLException {
        entityManager.persist(expectedPayment1);
        entityManager.persist(expectedPayment2);
        entityManager.flush();

        List<Payment> payments = mySqlPaymentRepository.findAllByBankId(5);
        assertThat(payments.isEmpty());
    }

    @Test
    public void storeCreatesNewPayment() throws Exception {

    }

    @Test
    public void updateChangesPayment() throws Exception {

    }

    @Test
    public void deleteRemovesPayment() throws Exception {

    }
}