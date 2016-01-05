package de.fhk.jaxws.prakt2.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import de.fhk.jaxws.prakt2.service.bean.Account;
import de.fhk.jaxws.prakt2.service.bean.Customer;
import de.fhk.jaxws.prakt2.service.bean.CustomerDAO;
import de.fhk.jaxws.prakt2.service.bean.CustomerDAOFactory;

@WebService(serviceName = "BankService",
		portName = "BankServicePort",	
		targetNamespace = "http://jawxs.fhk.prakt2/jaxws/bankservice")

@SOAPBinding(style=SOAPBinding.Style.RPC,use=SOAPBinding.Use.LITERAL,
			parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)

public class BankService {
	
	@WebMethod
	public boolean createCustomer(String name){
		
		Customer customer = new Customer(name);
		CustomerDAO customerDAO = CustomerDAOFactory.getInstance();
		return customerDAO.addCustomer(customer);
	}
	
	@WebMethod
	public long createAcccount(String name){
		
		CustomerDAO customerDAO = CustomerDAOFactory.getInstance();
		Customer customer = customerDAO.getCustomerByName(name);
		
		Account account = new Account(50, 186437);
		return 0;
	}

}
