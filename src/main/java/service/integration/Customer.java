package service.integration;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class implements the information available on a customer
 */
public class Customer {

	//-------------------------------------------------------------------
	// Class properties
	
	// Name of the customer (Smith, John)
	private String name;
	// Date of birth
	private Date birthday;
	// Current debt
	private BigDecimal debt;
	
	//-------------------------------------------------------------------
	// Class methods
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @return the debt
	 */
	public BigDecimal getDebt() {
		return debt;
	}
	
	/**
	 * Builds a Customer object with the appropiate information
	 * @param name Customer name
	 * @param birthday Birth date of the client
	 * @param debt Current debt
	 */
	public Customer(String name, Date birthday, BigDecimal debt) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.debt = debt;
	}
	
	
}
