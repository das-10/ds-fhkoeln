package de.thkoeln.pattern.observer;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.thkoeln.pattern.entities.CD;
import de.thkoeln.pattern.impl.ShoppingCardFactory;
import de.thkoeln.pattern.impl.ShoppingCardImpl;

public class InvoiceTest {
	private static Invoice invoice;
	private static ShoppingCardImpl shoppingCardImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		invoice = new Invoice();
		shoppingCardImpl = (ShoppingCardImpl) ShoppingCardFactory.getInstance();
		shoppingCardImpl.addObserver(invoice);
		shoppingCardImpl.addArticle(new CD("CD3", 25));
		shoppingCardImpl.addArticle(new CD("CD4", 30));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInvoiceShouldEquals55() {
		assertEquals(55, invoice.getSumme());
	}

}
