/**
 * @author sereni Dongmo
 */

package de.thkoeln.pattern.impl;

import de.thkoeln.pattern.dao.ShoppingCardDAO;

public class ShoppingCardFactory {
	
	 private static final class InstanceHolder {
		    static final ShoppingCardDAO INSTANCE = new ShoppingCardImpl();
		  }
	
	private  ShoppingCardFactory() {}
	
	public static ShoppingCardDAO getInstance(){
		return InstanceHolder.INSTANCE;
	}

}
