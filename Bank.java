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
		long id = ((long)Math.random())*1000000000L + 1;
		
		while(ledger.containsKey(id)) {			//if ledger already contains this id then find another id
			id = ((long)Math.random())*1000000000L + 1;
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
	 * Name: createCustomer
	 * Developers: Taha Salman
	 * Purpose: This method is used to create a new customer
	 * Inputs: N/A
	 * Outputs: returns a random percentage between 0 and 100 
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */	
	public static void createCustomer(HashMap ledger, HashMap customerRecords, Scanner stringReader) {
		System.out.println("Please enter your name:");
		String customerName = stringReader.nextLine();			//Read Customer Name
		long customerId = generateUniqueId(ledger);				//assign customer a unique id
		double customerDiscount = generateRandomPercentage();	//assign a random discount percentage
		
		//Need try catch block in case customer discount is invalid and exception is thrown by constructor or any other error while creating account
		try {				
			Customer customer = new Customer(customerName,customerId,customerDiscount);	//create a new customer
			ArrayList<BankAccount> accounts = new ArrayList(); 		//create an empty arraylist of bank accounts to assign to customer id
			
			ledger.put(customerId, accounts);		//add customer id and empty list of bank accoubts to ledger
			customerRecords.put(customerId, customer);	//map customer id to customer
			
			System.out.println("Your account has been created!");
			System.out.println("We advise you to create a bank account for yourself now :) ");
		}
		catch(Exception e){
			System.out.println("There was an error creating the account. Was the discount rate correct?");
		}
		
		
	}
	
	public static void main(String [] args) {
		
		HashMap<Long,ArrayList<BankAccount>> ledger = new HashMap();	//initialize a hashmap to store customer ids and accounts associated to then
		HashMap<Long,Customer> customerRecords = new HashMap();			//initialize a hashmap to map customer Ids to customers
		
		char option = ' '; 											//Options variable initialized. This will map to all user commands
		Scanner stringReader = new Scanner(System.in);				//Create scanner object to take string inputs
		Scanner numReader = new Scanner (System.in);				//Create scanner to take number inputs
		
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
					createCustomer(ledger,customerRecords,stringReader);		//CREATE NEW CUSTOMER
					break;
				case 'b':
					//CREATE NEW BANK ACCOUNT
					break;
				case 'c':
					//GET BALANCE
					break;
				case 'd':
					//DEPOSIT MONEY
					break;
				case 'e':
					//WITHDRAW MONEY
					break;
				case 'f':
					//TRANSFER MONEY
					break;
				case 'g':
					//QUIT
					break;
				default:
					//INVALID INPUT TRY AGAIN
			}
		}
	}
}
