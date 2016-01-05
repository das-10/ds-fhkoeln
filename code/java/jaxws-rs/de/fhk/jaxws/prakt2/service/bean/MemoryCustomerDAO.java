/**
 * 
 */
package de.fhk.jaxws.prakt2.service.bean;

import java.util.HashMap;

/**
 * @author serenidongmo
 *
 */
public class MemoryCustomerDAO implements CustomerDAO {
	
	HashMap<String, Customer> customerMap;

	/* (non-Javadoc)
	 * @see de.fhk.jaxws.prakt2.service.bean.CustomerDAO#createCustomer(de.fhk.jaxws.prakt2.service.bean.Customer)
	 */
	public MemoryCustomerDAO() {
		customerMap = new HashMap<String, Customer>();
	}
	
	@Override
	public boolean addCustomer(Customer customer) {
		if (customerMap.containsKey(customer.getName()))
			return false;
		customerMap.put(customer.getName(), customer);
		return true;
	}

	/* (non-Javadoc)
	 * @see de.fhk.jaxws.prakt2.service.bean.CustomerDAO#getCustomerByName(java.lang.String)
	 */
	@Override
	public Customer getCustomerByName(String name) {
		return customerMap.get(name);
	}
	
	@Override
	public boolean updateCustomer(Customer Customer){
		return false;
	}

}