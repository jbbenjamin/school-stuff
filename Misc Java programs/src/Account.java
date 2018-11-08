import java.util.Date;

public class Account {
	private int id = 0;
	private double balance = 0;
	private double annualInterestRate = 0;
	private Date dateCreated;
	
	public Account() {
	}
	
	public Account(int newId, double newBalance) {
		id = newId;
		balance = newBalance;
	}
	
	public int getId() {
		return id;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	public void setId(int newId) {
		id = newId;
	}
	
	public void setBalance(double newBalance) {
		balance = newBalance;
	}
	
	public void setAnnualInterestRate(double newAnnualInterestRate) {
		annualInterestRate = newAnnualInterestRate/100;
	}
	
	public Date getDateCreated() {
		dateCreated = new Date();
		return dateCreated;
		
	}
	
	public double getMonthlyInterestRate() {
		return annualInterestRate/12;
	}
	
	public double getMonthlyInterest() {
		return balance * (annualInterestRate/12);
	}
	
	public void withdraw(double withdrawAmount) {
		balance -= withdrawAmount;
	}
	
	public void deposit(double depositAmount) {
		balance += depositAmount;
	}
}
