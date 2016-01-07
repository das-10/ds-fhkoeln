package de.thkoeln.pattern.impl;

import java.util.Observable;

public class ShoppingCardDispatcher extends Thread {
	private ShoppingCardImpl shoppingCardImpl;
	
	public ShoppingCardDispatcher(Observable shoppingCardImpl){
		shoppingCardImpl = (ShoppingCardImpl) shoppingCardImpl;
	}

	public void run() {
		this.shoppingCardImpl.notifyObservers();
	}

}
