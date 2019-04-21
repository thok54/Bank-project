package ejercicio.banco.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class Payment implements InternalDto {

    private int paymentId;
    private int bankId;
    private int userId;
    private float amount;

    public Payment(int id) {
        this.paymentId = id;
    }

    public Payment(int paymentId, int bankId, int userId, float amount) {
        this.paymentId = paymentId;
        this.bankId = bankId;
        this.userId = userId;
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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
                .add("paymentId=" + paymentId)
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
        return paymentId == payment.paymentId &&
                bankId == payment.bankId &&
                userId == payment.userId &&
                Float.compare(payment.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, bankId, userId, amount);
    }
}
