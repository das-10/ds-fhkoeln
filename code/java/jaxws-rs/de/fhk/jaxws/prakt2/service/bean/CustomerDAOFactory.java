package de.fhk.jaxws.prakt2.service.bean;

public class CustomerDAOFactory {
	
	private static final class InstanceHolder {
	    static final CustomerDAO INSTANCE = new MemoryCustomerDAO();
	  }
	
	public static CustomerDAO getInstance(){
		return InstanceHolder.INSTANCE;
	}

}
