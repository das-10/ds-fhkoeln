package de.fhk.jaxws.prakt2.service.bean;

public class Account {
	private int stand;
	private int number;
	
	/**
	 * @param stand
	 * @param number
	 */
	public Account(int stand, int number) {
		this.stand = stand;
		this.number = number;
	}
	/**
	 * @return the stand
	 */
	public int getStand() {
		return stand;
	}
	/**
	 * @param stand the stand to set
	 */
	public void setStand(int stand) {
		this.stand = stand;
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
}
