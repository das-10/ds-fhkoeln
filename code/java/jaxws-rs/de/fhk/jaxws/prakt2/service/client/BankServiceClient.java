package de.fhk.jaxws.prakt2.service.client;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;


public class BankServiceClient {
    private final static QName qName = new QName(
    		"http://jawxs.fhk.prakt2/jaxws/bankservice", "BankService");
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 if (args.length != 1) {
		      System.out
		          .println("Specify the URL of the OrderProcess Web Service");
		      System.exit(-1);
		    }
		 
		 URL url = getWSDLURL(args[0]);
		 BankService_Service bankService = new BankService_Service(url, qName);
		 BankService port = bankService.getBankServicePort();
		 boolean customerCreated = port.createCustomer("Peter");
		 System.out.println("Peter Created:"+customerCreated);
		 
	}
	
	private static URL getWSDLURL(String urlStr) {
	    URL url = null;
	    try {
	      url = new URL(urlStr);
	    } catch (MalformedURLException e) {
	      e.printStackTrace();
	      throw new RuntimeException(e);
	    }
	    return url;
	  }

}
