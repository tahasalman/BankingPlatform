/**
 * File Name: Bank.java
 * Developers: Taha Salman
 * Purpose: This file contains the Bank class which will run the UI for the application
 * Inputs: N/A
 * Outputs: N/A
 * Modifications: N/A
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Bank {
	
	
	/*
	 * Name: generateUniqueId
	 * Developers: Taha Salman
	 * Purpose: This method is used to generate a unique ID for new customers
	 * Inputs: A HashMap with key equals to customer Id and value is the list of accounts linked to it
	 * Outputs: returns a unique 9 digit ID 
	 * Side-Effects: N/A
	 * Special Notes: depends on the ledger variable within this class
	 */	
	public static long generateUniqueId(HashMap<Long,ArrayList<BankAccount>> ledger) {
		long id = (long)((Math.random())*1000000000L + 1);
		
		while(ledger.containsKey(id)) {			//if ledger already contains this id then find another id
			id = (long)((Math.random())*1000000000L + 1);
		}
		
		return id;								//return a unique id when found
	}
	
	
	/*
	 * Name: generateRandomPercentage
	 * Developers: Taha Salman
	 * Purpose: This method is used to generate a random percentage to be used for discount
	 * Inputs: N/A
	 * Outputs: returns a random percentage between 0 and 100 
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */	
	public static double generateRandomPercentage() {
		return Math.random()*100 +1; 
	}
	
	
	/*
	 * Name: verifyAccoumt
	 * Developers: Taha Salman
	 * Purpose: This method is runs the UI that verifies if user is indeed a customer
	 * Inputs: Ledger as Hashmap - key is customer id, value is list of associated accounts, 2 scanner objects, one to read strings other to read number inputs
	 * Outputs: returns customer id if customer verified otherwise returns -1 
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */	
	public static long verifyAccount(HashMap ledger, Scanner stringReader, Scanner numReader) {
		char isCustomer = ' ';		//this char will store the response of whether user is customer or not
		
		//Check if user is customer 
		while(isCustomer!='y' && isCustomer!='n') {					//Keep looping until user types valid response
			System.out.println("Are you a customer? Type 'y' for yes and 'n' for no");
			isCustomer = stringReader.next().charAt(0);
		}
		
		if(isCustomer=='y') {			//If user is customer check if their customer id exists within ledger
			
			boolean isValidId = false;	//boolean to decide if user entered valid ID
			int tries = 0;				//allow user 3 tries to input valid ID 
			long customerId=0;
			
			while(tries<3 && !isValidId) {		//keep prompting user to enter id until id is correct or they run out of allowed tries (3)
				
				System.out.println("Please enter your Customer ID:");
				try {												//try catch block because nextLong() returns exception if there is an invalid input type
					customerId = numReader.nextLong();			//read customer Id 
					if(ledger.containsKey(customerId)) 		//check if ledger contains an account with this id
						isValidId = true;
				}
				catch(Exception e) {
					System.out.println("Please input numerical digits only!");
					numReader.nextLine();			//to clear buffer
					continue;
					
				}
				finally {
					tries++;
				}
			}
			
			//If id entered is valid, return customer id
			if(isValidId) {
				return customerId;
			}
		}
		
		return -1;		//otherwise return -1
			
	}
	
	
	/*
	 * Name: createCustomer
	 * Developers: Taha Salman
	 * Purpose: This method is used to create a new customer
	 * Inputs: Ledger hashmap containing bank accounts mapped to customer ids, 
	 * 			customerRecords which contains customer ids mapped to customers, and a Scanner object
	 * Outputs: N/A 
	 * Side-Effects: Allows customers to create an account without any bank accounts necessarily. So in the ledger their would be customer accounts mapped to an empty list of accounts
	 * Special Notes: N/A
	 */	
	public static void createCustomer(HashMap ledger, HashMap customerRecords, Scanner stringReader) {
		System.out.println("Please enter your name:");
		String customerName = stringReader.next();			//Read Customer Name
		long customerId = generateUniqueId(ledger);				//assign customer a unique id
		double customerDiscount = generateRandomPercentage();	//assign a random discount percentage
		
		//Need try catch block in case customer discount is invalid and exception is thrown by constructor or any other error while creating account
		try {				
			Customer customer = new Customer(customerName,customerId,customerDiscount);	//create a new customer
			ArrayList<BankAccount> accounts = new ArrayList(); 		//create an empty arraylist of bank accounts to assign to customer id
			
			ledger.put(customerId, accounts);			//add customer id and empty list of bank accounts to ledger
			customerRecords.put(customerId, customer);	//map customer id to customer
			
			System.out.println("Your account has been created!");
			System.out.println("Your customer ID is: "+customerId + " Please take note of it as you need it to create/access your bank accounts!");
			System.out.println("We advise you to create a bank account for yourself now :) ");
		}
		catch(Exception e){
			System.out.println("There was an error creating the account. Was the discount rate correct?");
		}
		
		
	}
	
	
	/*
	 * Name: createBankAccount
	 * Developers: Taha Salman
	 * Purpose: This method is used to create a new bank account
	 * Inputs: Ledger hashmap containing bank accounts mapped to customer ids, two scanner objects - one to read strings, 
	 * 			other to read numbers
	 * Outputs: N/A 
	 * Side-Effects: N/A
	 * Special Notes: Customers are allowed to create accounts with 0 dollars (no less though obviously :p) 
	 */	
	public static void createBankAccount(HashMap<Long,ArrayList<BankAccount>> ledger, Scanner numReader, Scanner stringReader) {
		
		long customerId = verifyAccount(ledger,numReader,stringReader);		//call verifyAccount method to verify if user is customer
		
		if(customerId != -1) {		//if user is customer 
			char accountType = ' ';
			
			//Keep prompting user for account type until they provide you with correct input
			while(accountType!='c' && accountType!='s') {
				System.out.println("Your account has been verified! What type of account would you "
						+ "like to create? Press 'c' for checkings and 's' for savings");
				accountType = stringReader.next().charAt(0);
			}
			
			if(accountType=='c') {		//if user entered c create a checkings account
				ArrayList<BankAccount> accountsForCustomer;
				int accountNum;
				double balance = -1;
				
				//keep prompting user to enter a valid amount of money to initially deposit
				while(balance<0) {
					System.out.println("How much money would you like to deposit in your new account? (Please enter a valid non negative amount)");
					try {
						balance = numReader.nextDouble();
					}
					catch(Exception e) {
						System.out.println("Please enter a valid number!");
						numReader.nextLine();			//to clear buffer
						continue;
					}
				}
				
				accountsForCustomer = ledger.get(customerId);				//Get the list of accounts associated with this customer id
				accountNum = accountsForCustomer.size()+1;					//Each account for the customer gets assigned a number denoting its position in the list of the customers accounts
				
				CheckingAccount account = new CheckingAccount(balance,accountNum);		//Create a new account
				
				accountsForCustomer.add(account);							//add the newly created account to list
				ledger.put(customerId, accountsForCustomer);				//update ledger with new account
				
				System.out.println("Your account has been created! Your account number is " + accountNum);
			}
			else {						//else create a savings account
				ArrayList<BankAccount> accountsForCustomer;
				int accountNum;
				double balance = -1;
				
				//keep prompting user to enter a valid amount of money to initially deposit
				while(balance<0) {
					System.out.println("How much money would you like to deposit in your new account? (Please enter a valid non negative amount");
					try {
						balance = numReader.nextDouble();
					}
					catch(Exception e) {
						System.out.println("Please enter a valid number!");
						numReader.nextLine();		//to clear buffer
					}

				}
				
				accountsForCustomer = ledger.get(customerId);				//Get the list of accounts associated with this customer id
				accountNum = accountsForCustomer.size()+1;					//Each account for the customer gets assigned a number denoting its position in the list of the customers accounts
				
				SavingsAccount account = new SavingsAccount(balance, accountNum);		//Create a new account
				
				accountsForCustomer.add(account);							//add the newly created account to list
				ledger.put(customerId, accountsForCustomer);				//update ledger with new account
				
				System.out.println("Your account has been created! Your account number is " + accountNum);
			}
		}
		
		else {							//otherwise tell user to create a customer account first
			System.out.println("You need to be a customer to create a bank account. Please create your"
					+ " customer account before creating a bank account. You are being redirected to the main menu");
		}
	}
	
	
	/*
	 * Name: selectAccount
	 * Developers: Taha Salman
	 * Purpose: This method displays all the accounts a user has and prompts them to select one to carry out an operation
	 * Inputs: ArrayList of bank accounts and a scanner object
	 * Outputs: Bank account selected otherwise returns null
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */	
	
	public static BankAccount selectAccount(ArrayList<BankAccount> accounts, Scanner numReader) {
		int size = accounts.size();		//find size once rather than everytime within loop
		
		//If there are no accounts then just return null
		if(size <1)
			return null;
		
		
		HashMap<Integer,BankAccount> accountsMapped = new HashMap<Integer, BankAccount>();;	//initialize a hashmap to map account numbers to accounts
		int accountNum;
		String accountType;
		
		
		System.out.println("Your accounts are as follows");
		
		for(int i=0; i<size;i++) {
			accountNum = accounts.get(i).getAccountNum();
			accountType = accounts.get(i).getType();
			
			System.out.println("Account Number: "+ accountNum + "   | Account Type: " + accountType);
			
			accountsMapped.put(accountNum, accounts.get(i));
		}
		
		System.out.println();
		
		accountNum = -1;  //set accountNum to -1 to allow the following loop to run
		
		
		
		while(!accountsMapped.containsKey(accountNum)) {		//keep asking user to select account until user types a valid account number
			System.out.println("Please type the account number of the account you wish to call this action on:");
			try{
				accountNum = numReader.nextInt();
			}
			catch(Exception e) {
				System.out.println("PLEASE ENTER A VALID ACCOUNT NUMBER!!!!");
				numReader.nextLine();			//to clear buffer
				continue;
			}
		}
		
		return accountsMapped.get(accountNum);		//return the bank account associated with account id
		
	}
	
	/*
	 * Name: getBalance
	 * Developers: Taha Salman
	 * Purpose: This method allows user to view their balance on their accounts
	 * Inputs: Ledger hashmap containing bank accounts mapped to customer ids, two scanner objects - one to read strings, 
	 * 			other to read numbers
	 * Outputs: N/A 
	 * Side-Effects: N/A
	 * Special Notes: N/A 
	 */	
	public static void getBalance(HashMap<Long,ArrayList<BankAccount>> ledger, Scanner numReader, Scanner stringReader) {
		long customerId = verifyAccount(ledger,numReader,stringReader);
		
		if(customerId != -1) {			//if customer has been verified
			ArrayList<BankAccount> accounts = ledger.get(customerId);
			
			BankAccount selectedAccount = selectAccount(accounts,numReader);
			
			if(selectedAccount==null) {			//If user does not have any bank accounts exit
				System.out.println("You have no bank accounts associated with this id :( ");
			}
			else									//return balance for selected account	
				System.out.println("Your balance in this account is " + selectedAccount.getBalance());
		}
		else {							//otherwise tell user to create a customer account first
			System.out.println("You need to be a customer to view your balance. Please create your"
					+ " customer account. You are being redirected to the main menu");
		}
	}
	
	
	/*
	 * Name: deposit
	 * Developers: Taha Salman
	 * Purpose: This method allows user to deposit money into their acocunt
	 * Inputs: Ledger hashmap containing bank accounts mapped to customer ids, two scanner objects - one to read strings, 
	 * 			other to read numbers
	 * Outputs: N/A 
	 * Side-Effects: N/A
	 * Special Notes: N/A 
	 */	
	public static void deposit(HashMap<Long,ArrayList<BankAccount>> ledger, Scanner numReader, Scanner stringReader) {
		long customerId = verifyAccount(ledger,numReader,stringReader);
		
		if(customerId != -1) {			//if customer has been verified
			ArrayList<BankAccount> accounts = ledger.get(customerId);
			
			BankAccount selectedAccount = selectAccount(accounts,numReader);
			
			if(selectedAccount == null) {			//If user does not have any bank accounts exit
				System.out.println("You have no bank accounts associated with this id :( ");
			}
			else {
				double amountToDeposit;					
				boolean depositSuccessful=false;
				
				
				while(!depositSuccessful) {				//keep looping until deposit successful
					System.out.println("How much money would you like to deposit?");
					try{
						amountToDeposit = numReader.nextDouble();			//take user input as double
						depositSuccessful = selectedAccount.deposit(amountToDeposit);		//store boolean return value in depositSuccessful after calling deposit method
						if(!depositSuccessful)
							System.out.println("Deposit unsuccessful :( Please try again.");
					}
					catch(Exception e){		
						System.out.println("Please enter a valid value!");
						numReader.nextLine(); //to clear buffer
						continue;
					}
					
				}
						
				System.out.println("Your money has been successfully deposited! Your new balance is: "+ selectedAccount.getBalance());
					
				}
		}	
		else {							//otherwise tell user to create a customer account first
			System.out.println("You need to be a customer to deposit money into a bank account. Please create your"
					+ " customer account. You are being redirected to the main menu");
		}
	}
	
	
	/*
	 * Name: withdraw
	 * Developers: Taha Salman
	 * Purpose: This method allows user to withdraw money from their acocunt
	 * Inputs: Ledger hashmap containing bank accounts mapped to customer ids, two scanner objects - one to read strings, 
	 * 			other to read numbers
	 * Outputs: N/A 
	 * Side-Effects: N/A
	 * Special Notes: N/A 
	 */	
	public static void withdraw(HashMap<Long,ArrayList<BankAccount>> ledger, Scanner numReader, Scanner stringReader) {
		long customerId = verifyAccount(ledger,numReader,stringReader);
		
		if(customerId != -1) {			//if customer has been verified
			ArrayList<BankAccount> accounts = ledger.get(customerId);
			
			BankAccount selectedAccount = selectAccount(accounts,numReader);
			
			if(selectedAccount == null) {			//If user does not have any bank accounts exit
				System.out.println("You have no bank accounts associated with this id :( ");
			}
			else {
				double amountToWithdraw=0;					
				boolean withdrawalSucceeded = false;
				
				while(!withdrawalSucceeded) {				//keep looping until withdrawal successful
					System.out.println("How much money would you like to withdraw? Your account balance is " + selectedAccount.getBalance());
					try{
						amountToWithdraw = numReader.nextDouble();
						withdrawalSucceeded = selectedAccount.withdraw(amountToWithdraw);
					}
					catch(Exception e) {
						System.out.println("Please enter a valid value");
						numReader.nextLine(); //to clear buffer
						continue;
					}
				}		
				
				System.out.println("You have successfully withdrawn " + amountToWithdraw+ " Your new balance is"
								+ selectedAccount.getBalance());
					
			}	
		}
		else {							//otherwise tell user to create a customer account first
			System.out.println("You need to be a customer to deposit money into a bank account. Please create your"
					+ " customer account. You are being redirected to the main menu");
		}
	}
	
	
	/*
	 * Name: transfer
	 * Developers: Taha Salman
	 * Purpose: This method allows user to transfer money from their account to another 
	 * Inputs: Ledger hashmap containing bank accounts mapped to customer ids, two scanner objects - one to read strings, 
	 * 			other to read numbers
	 * Outputs: N/A 
	 * Side-Effects: Users are allowed to transfer money from one account to the same one if they choose to do so.
	 * Special Notes: N/A 
	 */
	public static void transfer(HashMap<Long,ArrayList<BankAccount>> ledger, Scanner numReader, Scanner stringReader) {
		long customerId = verifyAccount(ledger,numReader,stringReader);
		
		if(customerId != -1) {			//if customer has been verified
			ArrayList<BankAccount> accounts = ledger.get(customerId);
			
			BankAccount accountToTransferFrom = selectAccount(accounts,numReader);
			
			if(accountToTransferFrom == null) {			//If user does not have any bank accounts exit
				System.out.println("You have no bank accounts associated with this id :( ");
			}
			else {							//user has atleast one bank account
				
				BankAccount accountToTransferTo = selectAccount(accounts,numReader);
				
				double amountToTransfer=0;					
				boolean transferSuccessful = false;
				
				while(!transferSuccessful) {				//keep looping until transfer successful
						System.out.println("How much money would you like to transfer? Your account balance is " + accountToTransferFrom.getBalance());
						try {
							amountToTransfer = numReader.nextDouble();
							transferSuccessful = accountToTransferFrom.transfer(amountToTransfer,accountToTransferTo);
						}
						catch(Exception e){
							System.out.println("Please enter a valid value");
							numReader.nextLine();
							continue;
						}
				}
						
				System.out.println("You have successfully transferred " + amountToTransfer+ " Your new balance in account " + 
						accountToTransferFrom.getAccountNum() + " is " + accountToTransferFrom.getBalance() +" | You new balance in account "
						+ accountToTransferTo.getAccountNum() + " is "	+accountToTransferTo.getBalance());
			}	
		}
		else {							//otherwise tell user to create a customer account first
			System.out.println("You need to be a customer to deposit money into a bank account. Please create your"
					+ " customer account. You are being redirected to the main menu");
		}
	}
	
	
	public static void main(String [] args) {
		
		HashMap<Long,ArrayList<BankAccount>> ledger = new HashMap();	//initialize a hashmap to store customer ids and accounts associated to then
		HashMap<Long,Customer> customerRecords = new HashMap();			//initialize a hashmap to map customer Ids to customers
		
		char option = ' '; 											//Options variable initialized. This will map to all user commands
		Scanner stringReader = new Scanner(System.in);				//Create scanner object to take string inputs
		Scanner numReader = new Scanner(System.in);				//Create scanner to take number inputs
		
		System.out.println("Welcome to The Bank!");
		
		while(option != 'g') {					//when option = g it means quit
			//Display all the possible options to user
			System.out.println("Press 'a' to create a new customer");
			System.out.println("Press 'b' to create a new bank account");
			System.out.println("Press 'c' to get your balance");
			System.out.println("Press 'd' to deposit money into your account");
			System.out.println("Press 'e' to withdraw money from an account");
			System.out.println("Press 'f' to make a tranfer between your accounts");
			System.out.println("Press 'g' to quit :( ");
			
			option=(stringReader.next().charAt(0));		//read user input, only take the first char if they entered more than 2 chars
			
			
			//Redirect to appropriate command based on input
			switch(option) {
				case 'a':
					createCustomer(ledger,customerRecords,stringReader);	//CREATE NEW CUSTOMER
					break;
				case 'b':
					createBankAccount(ledger,numReader,stringReader);		//CREATE NEW BANK ACCOUNT
					break;
				case 'c':
					getBalance(ledger,numReader,stringReader);				//GET BALANCE
					break;
				case 'd':
					deposit(ledger, numReader, stringReader); 				//DEPOSIT MONEY
					break;
				case 'e':
					withdraw(ledger, numReader, stringReader); 				//WITHDRAW MONEY
					break;
				case 'f':
					transfer(ledger, numReader, stringReader);				//TRANSFER MONEY
					break;
				case 'g':
					continue;												//QUIT
				default:
					System.out.println("Invalid input. Try again!");
			}
			
			System.out.println();
		}
	}
}
