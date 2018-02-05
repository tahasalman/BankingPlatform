/**
 * File Name: 
 * Developers: Taha Salman
 * Purpose: 
 * Inputs: N/A
 * Outputs: N/A
 * Modifications: N/A
 */
public abstract class BankAccount {
	private double balance;
	private int accountNum;
	
	/*
	 * Name: BankAccount
	 * Developers: Taha Salman
	 * Purpose: This is a constructor for BankAccount class
	 * Inputs: balance as a double and account num as an int
	 * Outputs: N/A
	 * Side-Effects: N/A
	 * Special Notes: If a negative number for balance is entered, a bank account is created with 0 balance
	 */
	public BankAccount(double balance,int accountNum) {
		if(balance<0)
			this.balance=0;
		else
			this.balance = balance;
		
		this.accountNum = accountNum;
	}
	
	
	/*
	 * Name: getBalance
	 * Developers: Taha Salman
	 * Purpose: This method is used to get the balance for the bank account
	 * Inputs: N/A
	 * Outputs: returns the balance in the bank account 
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public double getBalance() {
		return this.balance;
	}
	
	
	/*
	 * Name: deposit
	 * Developers: Taha Salman
	 * Purpose: This method is used to deposit money into the bank account
	 * Inputs: Amount that user wants to deposit into account
	 * Outputs: True - if deposit was successful, False - otherwise
	 * Side-Effects: Total balance is limited by max value of double. If amount deposited 
	 * 		is astronomically large (highly unlikely) the total balance will be incorrect
	 * Special Notes: N/A
	 */
	public boolean deposit(double amountToDeposit) {
		if(amountToDeposit>0) {		//check that the amount user wants to deposit is positive
			this.balance+=amountToDeposit;	//add specified money into bank
			return true;
		}
		return false;			//return false and do not change balance if incorrect amount is entered
	}
	
	
	/*
	 * Name: withdraw
	 * Developers: Taha Salman
	 * Purpose: This method is used to withdraw money from the bank account
	 * Inputs: Amount to withdraw
	 * Outputs: True - if withdrawal successful, false otherwise
	 * Side-Effects: N/A
	 * Special Notes: Balance is always 0 or more, so withdrawal amount always 
	 * 		has to be 0 or more 
	 */
	public boolean withdraw(double amountToWithdraw) {
		if(amountToWithdraw <= this.balance) {		//check that user has enough money to withdraw
			this.balance -= amountToWithdraw;		//subtract amount from balance in bank account
			return true;							//return true suggesting withdrawal was successful
		}
		return false;								//return false if withdrawal failed		
	}
	
	/*
	 * Name: transfer
	 * Developers: Taha Salman
	 * Purpose: This method is used to transfer money from the current bank account to another one
	 * Inputs: Amount to Transfer and the Bank account to transfer to
	 * Outputs: True if transfer successful, false otherwise
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public boolean transfer(double amountToTransfer, BankAccount secondAccount) {
		if(this.balance < amountToTransfer || amountToTransfer <0)				//if user does not have enough money in acocunt to transfer return false 
			return false;														//also if amount to transfer is a negative value return false
		
		this.balance -= amountToTransfer;	//first withdraw the money from the bank account
		
		return secondAccount.deposit(amountToTransfer);		//then deposit money into second amount and return whether deposit was successful or not
		
	}
	
	/*
	 * Name: getAccountNum
	 * Developers: Taha Salman
	 * Purpose: This method returns account number of the bank account
	 * Inputs: N/A
	 * Outputs: Account Number
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public int getAccountNum() {
		return this.accountNum;
	}
	
	
	/*
	 * Name: getType
	 * Developers: Taha Salman
	 * Purpose: This is an abstract method. It will be defined within the child classes 
	 * Inputs: N/A
	 * Outputs: The type of account
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public abstract String getType();
	
	
}
