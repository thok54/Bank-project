package main.java.ejercicio.banco.dto;

public class Account {
	
	//Variables
	private int userID;
	private String userName;
	private float money;
	private String iban;
	
	
	//Constructor
	public Account(int userID, String userName, float money, String iban) {
		this.userID = userID;
		this.userName = userName;
		this.money = money;
		this.iban = iban;
	}


	//Overrrite toString() method using String builder
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(userID);
		sb.append("; ");
		sb.append(userName);
		sb.append("; ");
		sb.append(money);
		sb.append("; ");
		sb.append(iban);
		
		String line = sb.toString();
		return line;		
	}


	
	//Getters and Setters
	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public float getMoney() {
		return money;
	}


	public void setMoney(float money) {
		this.money = money;
	}


	public String getIban() {
		return iban;
	}


	public void setIban(String iban) {
		this.iban = iban;
	}
		

}