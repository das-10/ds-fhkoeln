package de.thkoeln.pattern.factory;

import de.thkoeln.pattern.entities.CommercialGift;
import de.thkoeln.pattern.entities.Voucher;

public abstract class Gift {
	
	public abstract Voucher createVoucher();
	public abstract CommercialGift createCommercialGift();

}