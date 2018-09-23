package test.java.ejercicio.banco.repository;
import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.repository.CsvPaymentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.List;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CsvPaymentRepositoryTest {

    private CsvPaymentRepository repository;

    @Before
    public void setup() {
        this.repository = new CsvPaymentRepository();
    }


    @Test
    public void findAllTest(){

        //Given
        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTest.csv");

        //When
        List<Payment> payments = repository.findAll(paymentFileTest);

        //Then
        Assert.assertTrue("Not getting the right Amount", payments.get(1).getAmount() == 87.25);
    }


    @Test(expected = IllegalArgumentException.class)
    public void findAllThrowsExceptionWhenFileNotFoundTest(){
        //Given
        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTd2fd2est.csv");

        //When
        repository.findAll(paymentFileTest);
    }

    @Test
    public void findTest(){
        //When
        Payment payment = repository.find(20);
        //Then
        assertNull(payment);
    }


    @Test
    public void storePaymentTest(){
        //Given
        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTest.csv");

        //When
        List<Payment> payments = repository.findAll(paymentFileTest);
        Payment payment = payments.get(0);
        float money = payment.getAmount();
        repository.store(payment);

        //Then
        assertTrue("Payment should remain same", payment.getAmount()==money);

    }


    @Test
    public void updatePaymentTest(){
        //Given
        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTest.csv");
        //When
        List<Payment> payments = repository.findAll(paymentFileTest);
        Payment payment = payments.get(0);
        float money = payment.getAmount();
        repository.update(0, payment);
        //Then
        assertTrue("Payment should remain same", payment.getAmount()==money);
    }



    @Test
    public void deletePaymentTest(){
        //Given
        String paymentFileTest = String.join(File.separator, "src", "test", "resources", "csv", "paymentTest.csv");
        //When
        List<Payment> payments = repository.findAll(paymentFileTest);
        Payment payment = payments.get(0);
        float money = payment.getAmount();
        repository.delete(0);
        //Then
        assertTrue("Payment should remain same", payment.getAmount()==money);
    }

}
