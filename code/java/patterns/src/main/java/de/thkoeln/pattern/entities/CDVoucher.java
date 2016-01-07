package de.thkoeln.pattern.entities;

public class CDVoucher extends Voucher {
	
	public CDVoucher(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CDVoucher [value=" + value + "]";
	}

}
