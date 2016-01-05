package de.fhk.jaxws.prakt2.service.publisch;

import javax.xml.ws.Endpoint;
import de.fhk.jaxws.prakt2.service.BankService;

public class BankWebServicePublischer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Server STarted");
		Endpoint.publish("http://localhost:55555/bankservices",
				new BankService());
	}

}
