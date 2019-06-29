package ejercicio.banco.repository.jpa;

import ejercicio.banco.dto.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByBankId(Integer bankId);
}
