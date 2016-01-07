package de.thkoeln.pattern.impl;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.thkoeln.pattern.dao.ShoppingCardDAO;
import de.thkoeln.pattern.entities.Article;
import de.thkoeln.pattern.entities.CD;

public class ShoppindCArdRemoveTest {
	
	private static ShoppingCardDAO shoppingCardDAO;
	private Article article;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shoppingCardDAO = ShoppingCardFactory.getInstance();
	}
	
	@Before
	public void setUpBefore(){
	 article = new CD("CD3", 25);
	 shoppingCardDAO.addArticle(article);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		shoppingCardDAO = null;
	}

	@Test
	public void testRemoveArticleToCard() {
		assertTrue(shoppingCardDAO.deleteArticle(article));
	}
	
}
