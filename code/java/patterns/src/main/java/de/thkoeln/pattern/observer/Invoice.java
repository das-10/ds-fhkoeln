package de.thkoeln.pattern.observer;

import de.thkoeln.pattern.entities.Article;

public class Invoice extends ShoppingCardObserver {
	
	private int summe;
	public Invoice() {}
	
	@Override
	protected void doAction() {
		summe = 0;
		for (Article article : this.getArticles()) {
			summe += article.getValue();
		}
		this.setSumme(summe);
		
	}

	public int getSumme() {
		return summe;
	}

	public void setSumme(int summe) {
		this.summe = summe;
	}

	@Override
	public String toString() {
		return "Invoice [summe=" + summe +"€)"+ "]";
	}
	
}
