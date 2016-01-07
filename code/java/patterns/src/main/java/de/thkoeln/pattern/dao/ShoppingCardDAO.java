package de.thkoeln.pattern.dao;

import java.util.ArrayList;

import de.thkoeln.pattern.entities.Article;

public interface ShoppingCardDAO {
	
	public boolean addArticle(Article article);
	public boolean deleteArticle(Article article);
	public ArrayList<Article> getArticles();

}
