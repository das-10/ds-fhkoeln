package de.thkoeln.pattern.observer;

import de.thkoeln.pattern.entities.Article;

public class WebDisplay extends ShoppingCardObserver {
	
	public WebDisplay() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doAction() {
		for (Article article : this.getArticles()) {
			System.out.println("Article Name:"+article.getName()
					+"/"+ "Article Value:"+ article.getValue());
		}
		
	}
	
}
