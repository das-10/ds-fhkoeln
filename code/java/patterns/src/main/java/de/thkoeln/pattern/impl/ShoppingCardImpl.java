/**
 * @author sereni Dongmo
 */

package de.thkoeln.pattern.impl;

import java.util.ArrayList;
import java.util.Observable;
import de.thkoeln.pattern.dao.ShoppingCardDAO;
import de.thkoeln.pattern.entities.Article;

public class ShoppingCardImpl extends Observable implements ShoppingCardDAO{
	private ArrayList<Article> shoppingCardList;

	public ShoppingCardImpl() {
		shoppingCardList = new ArrayList<Article>();
	}

	public boolean addArticle(Article article) {
		boolean result = shoppingCardList.add(article);
		this.fireEvent();
		return result;

	}

	public boolean deleteArticle(Article article) {
		boolean result= shoppingCardList.remove(article);
		this.fireEvent();
		return result;

	}
	
	public ArrayList<Article> getArticles(){
		return shoppingCardList;
	}
	
	private void fireEvent(){
		
		ShoppingCardDispatcher shoppingCardDispatcher = new ShoppingCardDispatcher(this);
		shoppingCardDispatcher.start();;
		//this.setChanged();
		//this.notifyObservers();
	}
	
	public void setChanged(){
		super.setChanged();
	}

}
