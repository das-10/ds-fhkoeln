/**
 * @author sereni DOngmo
 */

package de.thkoeln.pattern.factory;

import de.thkoeln.pattern.entities.Article;
import de.thkoeln.pattern.entities.CD;
import de.thkoeln.pattern.impl.ShoppingCardFactory;
import de.thkoeln.pattern.impl.ShoppingCardImpl;
import de.thkoeln.pattern.observer.WebDisplay;

public class Client {

	public static void main(String[] args) {
		
		 WebDisplay WebDisplay;
	     ShoppingCardImpl shoppingCardImpl;
		 Article article;
		 
		 	WebDisplay = new WebDisplay();
			shoppingCardImpl = (ShoppingCardImpl) ShoppingCardFactory.getInstance();
			shoppingCardImpl.addObserver(WebDisplay);
			article = new CD("CD3", 25);
			shoppingCardImpl.addArticle(article);
		
	}

}
