package exercise.dto;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "PAYMENTS")
public class Payment implements InternalDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int bankId;
    private int userId;
    private float amount;

    public Payment() {
    }

    public Payment(int id) {
        this.id = id;
    }

    public Payment(int id, int bankId, int userId, float amount) {
        this.id = id;
        this.bankId = bankId;
        this.userId = userId;
        this.amount = amount;
    }

    public Payment(int bankId, int userId, float amount) {
        this.bankId = bankId;
        this.userId = userId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Payment.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("bankId=" + bankId)
                .add("userId=" + userId)
                .add("amount=" + amount)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id &&
                bankId == payment.bankId &&
                userId == payment.userId &&
                Float.compare(payment.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankId, userId, amount);
    }
}
