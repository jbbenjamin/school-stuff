
public class TestAccount {
	
	public static void main(String[] args){
		
		Account Account1 = new Account(1122, 20000);
		
		Account1.setAnnualInterestRate(4.5);
		
		Account1.withdraw(2500);
		
		Account1.deposit(3000);
		
		System.out.println("For Account1, created on " + Account1.getDateCreated() + " the balance is $" + Account1.getBalance() + " and the monthly interest is $" + Account1.getMonthlyInterest() + ".");
		
	}

}
