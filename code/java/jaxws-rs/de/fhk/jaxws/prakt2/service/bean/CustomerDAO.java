/**
 * 
 */
package de.fhk.jaxws.prakt2.service.bean;

/**
 * @author serenidongmo
 *
 */
public interface CustomerDAO {
	
	/**
	 * 
	 * @param customer the customer
	 * @return true if customer is created otherwise false
	 */
	public boolean addCustomer(Customer customer);
	
	/**
	 * 
	 * @param name customnerName to be created
	 * @return the customer
	 */
	public Customer getCustomerByName(String name);
	
	/**
	 * 
	 * @param customer the customer
	 * @return true if success otherwise false
	 */
	public boolean updateCustomer(Customer customer);

}
