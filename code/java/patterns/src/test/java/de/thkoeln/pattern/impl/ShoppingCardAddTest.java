/**
 * @author sereni Dongmo
 */

package de.thkoeln.pattern.impl;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.thkoeln.pattern.dao.ShoppingCardDAO;
import de.thkoeln.pattern.entities.Article;
import de.thkoeln.pattern.entities.CD;

public class ShoppingCardAddTest {
	
	private static ShoppingCardDAO shoppingCardDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		shoppingCardDAO = ShoppingCardFactory.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		shoppingCardDAO = null;
	}

	@Test
	public void testAddArcticleToCard() {
		Article article = new CD("CD3", 25);
		assertTrue(shoppingCardDAO.addArticle(article));
	}
	
	@Test
	public void testAddArcticleToCard_02() {
		Article article = new CD("DVD", 30);
		assertTrue(shoppingCardDAO.addArticle(article));
	}

}
