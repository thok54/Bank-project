package main.java.ejercicio.banco.dto;

public class Payment {

	//Variables
	private int paymentId;
	private int bankId;
	private int userId;
	private float amount;
	
	//Constructor
	public Payment(int paymentId, int bankId, int userId, float amount) {
		this.paymentId = paymentId;
		this.bankId = bankId;
		this.userId = userId;
		this.amount = amount;
	}

	
	
	//Getters and Setters
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
	
	
	
}
