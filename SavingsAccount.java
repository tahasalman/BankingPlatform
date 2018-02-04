/**
 * File Name: 
 * Developers: Taha Salman
 * Purpose: 
 * Inputs: N/A
 * Outputs: N/A
 * Modifications: N/A
 * 
 */
public class SavingsAccount extends BankAccount{
	
	/*
	 * Name: Savings Account
	 * Developers: Taha Salman
	 * Purpose: This is a constructor for SavingsAccount class which extends BankAccount class
	 * Inputs: Balance as a double value
	 * Outputs: N/A
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public SavingsAccount(double balance, int accountNum) {
		super(balance,accountNum);
	}
	
	
	/*
	 * Name: deposit
	 * Developers: Taha Salman
	 * Purpose: This method allows user to deposit money into their account. This method simply extends the superclass one
	 * Inputs: Amount to deposit, and discount percentage of customer
	 * Outputs: True - if deposit successful, false otherwise
	 * Side-Effects: N/A
	 * Special Notes: assuming discountPercent is a percentage between 0 and 100. 
	 */
	public boolean deposit(double amountToDeposit, double discountPercent) {
		if(discountPercent<0 || discountPercent>100) 		//verify that a valid discountPercent has been entered. If not return false.
			return false;
		
		double depositBonus = 1 + (discountPercent/100);		//calculate deposit bonus by adding to 1 dollar bonus the amount the customer earns via discount percentage
		amountToDeposit += depositBonus;						
		return super.deposit(amountToDeposit+depositBonus);		//call the deposit method from superclass
	}
	
	
	/*
	 * Name: withdraw
	 * Developers: Taha Salman
	 * Purpose: This method allows users to withdraw money from their savings account
	 * Inputs: amount to withdraw
	 * Outputs: true, if withdrawal successful, false otherwise
	 * Side-Effects: N/A
	 * Special Notes: witdrawal amount has to be greater than or equal to 1000
	 */
	public boolean withdraw(double amountToWithdraw, double discountPercent) {
		if(amountToWithdraw<1000)				//amount has to be greater than 1000, otherwise user cannot withdraw and return false
			return false;
		
		return super.withdraw(amountToWithdraw);
	}
	
	
	/*
	 * Name: transfer
	 * Developers: Taha Salman
	 * Purpose: This method allows users transfer money from their savings account to another account
	 * Inputs: amount to transfer and the bank account to transfer to
	 * Outputs: true, if transfer successful, false otherwise
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public boolean transfer(double amountToTransfer, BankAccount secondAccount) {
		return super.transfer(amountToTransfer, secondAccount);
	}
	
	
	/*
	 * Name: savings
	 * Developers: Taha Salman
	 * Purpose: This method displays the type of bank account 
	 * Inputs: N/A
	 * Outputs: The string "savings"
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public String getType() {
		return "savings";
	}
	

}
