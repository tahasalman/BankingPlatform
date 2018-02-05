/**
 * File Name: Customer.java
 * Developers: Taha Salman
 * Purpose: This file contains the Customer class which represents the
 *  blueprint for customers in this banking application
 * Inputs: N/A
 * Outputs: N/A
 * Modifications: N/A
 * 
 */
public class Customer {
	private String name;
	private long IdNum;
	private double discountPercentage;			//entered as value between 1 and 100
	
	
	/*
	 * Name: Customer
	 * Developers: Taha Salman
	 * Purpose: This is a constructor for the Customer class
	 * Inputs: Takes as input name of customer, a unique id, and discount percentage
	 * Outputs: N/A
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public Customer(String name, long id, double discount) throws Exception {
		if(discount<0 || discount >100)							//check if discount is within a valid range 
			throw new Exception("Invalid value for discount");	//throw exception if not
		
		this.name = name;
		this.IdNum = id;
		this.discountPercentage = discount;
	}
	
	
	/*
	 * Name: getDiscount
	 * Developers: Taha Salman
	 * Purpose: This method returns the discount for a specific customer
	 * Inputs: N/A
	 * Outputs: double discount percentage
	 * Side-Effects: N/A
	 * Special Notes: N/A
	 */
	public double getDiscount() {
		return this.discountPercentage;
	}
	
}
