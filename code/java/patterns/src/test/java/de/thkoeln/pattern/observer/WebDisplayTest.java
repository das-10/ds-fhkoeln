package de.thkoeln.pattern.observer;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.thkoeln.pattern.dao.ShoppingCardDAO;
import de.thkoeln.pattern.entities.Article;
import de.thkoeln.pattern.entities.CD;
import de.thkoeln.pattern.impl.ShoppingCardFactory;
import de.thkoeln.pattern.impl.ShoppingCardImpl;

public class WebDisplayTest {
	private static WebDisplay WebDisplay;
	private static ShoppingCardImpl shoppingCardImpl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		WebDisplay = new WebDisplay();
		
		shoppingCardImpl = (ShoppingCardImpl) ShoppingCardFactory.getInstance();
		shoppingCardImpl.addObserver(WebDisplay);
		shoppingCardImpl.addArticle(new CD("CD3", 25));
		shoppingCardImpl.addArticle(new CD("CD4", 30));
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		WebDisplay = null;
		shoppingCardImpl = null;
		WebDisplay = null;
	}

	@Test
	public void testShouldDisplayData() {
		//assertTrue(WebDisplay.getArticles().size()>0);
		assertEquals("CD3", WebDisplay.getArticles().get(0).getName());
		assertEquals(Integer.valueOf(25).intValue(), 
				WebDisplay.getArticles().get(0).getValue().intValue());
	}
	
	@Test
	public void testShouldDisplayData_01() {
		//assertTrue(WebDisplay.getArticles().size()>0);
		assertEquals("CD4", WebDisplay.getArticles().get(1).getName());
		assertEquals(Integer.valueOf(30).intValue(), 
				WebDisplay.getArticles().get(1).getValue().intValue());
	}

}
