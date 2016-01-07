package de.thkoeln.pattern.observer;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import de.thkoeln.pattern.dao.ShoppingCardDAO;
import de.thkoeln.pattern.entities.Article;

public abstract class  ShoppingCardObserver implements Observer {
	protected ArrayList<Article> articles;
	protected ShoppingCardDAO shoppingCardDAO;

	public void update(Observable observable, Object arg) {
	shoppingCardDAO = (ShoppingCardDAO) observable;
		this.setShoppingCardDAO((shoppingCardDAO));
		this.setArticles(shoppingCardDAO.getArticles());
		this.doAction();
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	private void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	public ShoppingCardDAO getShoppingCardDAO() {
		return shoppingCardDAO;
	}

	private void setShoppingCardDAO(ShoppingCardDAO shoppingCardDAO) {
		this.shoppingCardDAO = shoppingCardDAO;
	}
	
	protected abstract void doAction();
	
}
