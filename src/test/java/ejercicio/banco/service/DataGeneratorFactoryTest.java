package ejercicio.banco.service;

import ejercicio.banco.dto.DataType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static ejercicio.banco.dto.DataType.ACCOUNT;
import static ejercicio.banco.dto.DataType.BANK;
import static ejercicio.banco.dto.DataType.PAYMENT;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DataGeneratorFactoryTest {

    private static final String FILE_NAME = "file-name-test";

    @Mock
    private DataGeneratorFactory factory;

    @InjectMocks

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testGenerateCallsGetDataType() {
        factory.generate(BANK, FILE_NAME);
        //TODO: Something smells fishy here, does not pass test
        verify(factory).getDataType(BANK, FILE_NAME);
    }
}
