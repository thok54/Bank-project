package exercise.repository.jpa;

import exercise.dto.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByBankId(Integer bankId);
    //void store(Payment payment);
    //void update(Integer id, Payment payment);
    //void delete(Integer id);
}
