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
	
	/*
	 * Name: Checking Account
	 * Developers: Taha Salman
	 * Purpose: This is a constructor for CheckingAccount class which extends BankAccount class
	 * Inputs: Balance as a double value
	 * Outputs: N/A
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public CheckingAccount(double balance,int accountNum) {
		super(balance,accountNum);
	}
	
	
	/*
	 * Name: deposit
	 * Developers: Taha Salman
	 * Purpose: This method allows user to deposit money into their account. This method simply extends the superclass one
	 * Inputs: Amount to deposit
	 * Outputs: True - if deposit successful, false otherwise
	 * Side-Effects: N/A
	 * Special Notes: Assumes superclass method is implemented correctly. This method is redundant since it is just calling the superclass method;
	 * 				however, I have declared it here to allow flexibility to change it if needed for checking accounts.
	 */
	public boolean deposit(double amountToDeposit) {
		return super.deposit(amountToDeposit);
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
	 * Name: transfer
	 * Developers: Taha Salman
	 * Purpose: This method allows users transfer money from their checking account to another account
	 * Inputs: amount to transfer and the bank account to transfer to
	 * Outputs: true, if transfer successful, false otherwise
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public boolean transfer(double amountToTransfer, BankAccount secondAccount) {
		return super.transfer(amountToTransfer, secondAccount);
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
		return "checkings";
	}
	
}
