package de.thkoeln.pattern.entities;

public class DVDVoucher extends Voucher {
	
	public DVDVoucher(Integer value){
		this.value = value;
	}

	@Override
	public String toString() {
		return "DVDVoucher [value=" + value + "]";
	}

	
}
