/**
 * File Name: 
 * Developers: Taha Salman
 * Purpose: 
 * Inputs: N/A
 * Outputs: N/A
 * Modifications: N/A
 * 
 */
public class CheckingAccount extends BankAccount {
	private String type = "checking";
	
	/*
	 * Name: Checking Account
	 * Developers: Taha Salman
	 * Purpose: This is a constructor for CheckingAccount class which extends BankAccount class
	 * Inputs: Balance as a double value and account Number
	 * Outputs: N/A
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public CheckingAccount(double balance,int accountNum) {
		super(balance,accountNum);
	}
	

	/*
	 * Name: withdraw
	 * Developers: Taha Salman
	 * Purpose: This method allows users to withdraw money from their checking account
	 * Inputs: amount to withdraw and the discount applicable for the user
	 * Outputs: true, if withdrawal successful, false otherwise
	 * Side-Effects: N/A
	 * Special Notes: assuming discount entered is a valid percentage between 0 and 100
	 */
	public boolean withdraw(double amountToWithdraw, double discountPercent) {
		if(discountPercent<0 || discountPercent>100)				//verify that a valid discountPercent has been entered. otherwise return false
			return false;
		double withdrawalCharge = 1 - (discountPercent/100);		//1 dollar is the default charge, subtract from 1 the discount user receives		
		amountToWithdraw += withdrawalCharge; 				//add withdrawal charge to the total amount to be withdrawn
		return super.withdraw(amountToWithdraw);					//call the withdraw method from superclass with new withdrawal value				
	}
	
	
	/*
	 * Name: checkings
	 * Developers: Taha Salman
	 * Purpose: This method displays the type of bank account 
	 * Inputs: N/A
	 * Outputs: The string "checkings"
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public String getType() {
		return this.type;
	}
	
}
